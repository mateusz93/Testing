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
        Server server = getServer((ServerBuilder.server()).withCapacity(1));

        //when
        balancing(serversListWith(server), emptyListOfVirtualMachines());

        //then
        assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(0.0d));
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

    private Server getServer(ServerBuilder serverBuilder) {
        return serverBuilder.build();
    }

}
