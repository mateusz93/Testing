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
