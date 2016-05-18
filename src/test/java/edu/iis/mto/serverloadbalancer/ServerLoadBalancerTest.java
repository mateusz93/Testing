package edu.iis.mto.serverloadbalancer;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }

    @Test
    public void balancingServerWithNoVms() {
        //given
        Server server = get((ServerBuilder.server()).withCapacity(1));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines());

        //then
        assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(0.0d));
    }

    @Test
    public void balancingServerWithOneSlotCapacityAndOneSlotVm_fillsServerWithTheVm() {
        //given
        Server server = get((ServerBuilder.server()).withCapacity(1));
        VirtualMachine virtualMachine = get(VirtualMachineBuilder.vm().ofSize(1));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines(virtualMachine));

        //then
        assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(Server.MAXIMUM_LOAD));
        assertThat("server should contain the vm", server.contains(virtualMachine));
    }

    @Test
    public void balancingOneServerWithTenSlotsCapacityAndOneSlotVm_fillsTheServerWithTenPrecent() {
        //given
        Server server = get((ServerBuilder.server()).withCapacity(10));
        VirtualMachine virtualMachine = get(VirtualMachineBuilder.vm().ofSize(1));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines(virtualMachine));

        //then
        assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(10.0d));
        assertThat("server should contain the vm", server.contains(virtualMachine));
    }

    @Test
    public void balancingServerWithEnoughRoom_fillsTheServerWithAllVm() {
        //given
        Server server = get((ServerBuilder.server()).withCapacity(10));
        VirtualMachine virtualMachine1 = get(VirtualMachineBuilder.vm().ofSize(1));
        VirtualMachine virtualMachine2 = get(VirtualMachineBuilder.vm().ofSize(1));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines(virtualMachine1, virtualMachine2));

        //then
        assertThat(server, ServerVmsCountMatcher.hasAVmsCountOf(2));
        assertThat("server should contain the first vm", server.contains(virtualMachine1));
        assertThat("server should contain the second vm", server.contains(virtualMachine2));
    }

    @Test
    public void vmShouldBeBalancedOnLessLoadedServerFirst() {
        //given
        Server moreLoadedServer = get((ServerBuilder.server()).withCapacity(100).withCurrentLoadOf(50.0d));
        Server lessLoadedServer = get((ServerBuilder.server()).withCapacity(100).withCurrentLoadOf(40.0d));
        VirtualMachine virtualMachine = get(VirtualMachineBuilder.vm().ofSize(10));

        //when
        balancing(serversListWith(moreLoadedServer, lessLoadedServer), emptyListOfVirtualMachines(virtualMachine));

        //then
        assertThat("more loaded server should not contain the vm", !moreLoadedServer.contains(virtualMachine));
        assertThat("less loaded server should contain the vm", lessLoadedServer.contains(virtualMachine));
    }

    @Test
    public void balancingServerWithNotEnoughRoomShouldNotBeFilled() {
        //given
        Server server = get((ServerBuilder.server()).withCapacity(10).withCurrentLoadOf(99.0d));
        VirtualMachine virtualMachine = get(VirtualMachineBuilder.vm().ofSize(2));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines(virtualMachine));

        //then
        assertThat("server should not contain the vm", !server.contains(virtualMachine));
    }

    @Test
    public void balancingServerAndVms() {
        //given
        Server server1 = get((ServerBuilder.server()).withCapacity(4));
        Server server2 = get((ServerBuilder.server()).withCapacity(6));
        VirtualMachine virtualMachine1 = get(VirtualMachineBuilder.vm().ofSize(1));
        VirtualMachine virtualMachine2 = get(VirtualMachineBuilder.vm().ofSize(4));
        VirtualMachine virtualMachine3 = get(VirtualMachineBuilder.vm().ofSize(2));

        //when
        balancing(serversListWith(server1, server2), emptyListOfVirtualMachines(virtualMachine1, virtualMachine2, virtualMachine3));

        //then
        assertThat("server 1 should contain the vm1", server1.contains(virtualMachine1));
        assertThat("server 2 should contain the vm2", server2.contains(virtualMachine2));
        assertThat("server 1 should contain the vm3", server1.contains(virtualMachine3));
        assertThat(server1, CurrentLoadPercentageMatcher.hasCurrentLoadOf(75.0d));
        assertThat(server2, CurrentLoadPercentageMatcher.hasCurrentLoadOf(66.6d));
    }


    private VirtualMachine[] emptyListOfVirtualMachines(VirtualMachine... virtualMachines) {
        return virtualMachines;
    }

    private void balancing(Server[] servers, VirtualMachine[] virtualMachines) {
        new ServerLoadBalancer().balance(servers, virtualMachines);
    }

    private VirtualMachine[] emptyListOfVirtualMachines() {
        return new VirtualMachine[0];
    }

    private Server[] serversListWith(Server... servers) {
        return servers;
    }

    private <T> T get(Builder<T> builder) {
        return builder.build();
    }
}
