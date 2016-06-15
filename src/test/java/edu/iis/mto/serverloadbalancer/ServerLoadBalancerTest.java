package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVmsServerStayEmpty() {
		Server server = a(server().withCapacity(10));

		balance(serverListWith(server), emptyVmList());

		assertThat(server, hasLoadPercentage(0.0d));
	}

	@Test
	public void balancingServerWithOneVmAndServerSlot() {
		Server server = a(server().withCapacity(1));
		Vm vm = b(vm().sizeOf(1));

		balance(serverListWith(server), vmsListWith(vm));

		assertThat(server, hasLoadPercentage(100.0d));
		assertThat("the server should contain vm", server.contains(vm));
	}

	private Vm[] vmsListWith(Vm vm) {
		return new Vm[]{vm};
	}

	private VmBuilder vm() {
		return new VmBuilder();
	}

	private Matcher<? super Server> hasLoadPercentage(double expectedPercentage) {
		return new CurrentServerLoadPercentageMatcher(expectedPercentage);
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	public Server a(ServerBuilder builder) {
		return builder.build();
	}

	public Vm b(VmBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

	private Server[] serverListWith(Server server) {
		return new Server[]{server};
	}

	private Vm[] emptyVmList() {
		return new Vm[0];
	}

}
