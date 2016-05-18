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
        assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(100.0d));
        assertThat("server should contain the vm", server.cotains(virtualMachine));
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
