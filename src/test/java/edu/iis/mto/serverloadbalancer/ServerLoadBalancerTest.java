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
		Server server = getItem(server().withCapacity(10));

		balance(serverListWith(server), emptyVmList());

		assertThat(server, hasLoadPercentage(0.0d));
	}

	@Test
	public void balancingServerWithOneVmAndServerSlot() {
		Server server = getItem(server().withCapacity(1));
		Vm vm = getItem(vm().sizeOf(1));

		balance(serverListWith(server), vmsListWith(vm));

		assertThat(server, hasLoadPercentage(100.0d));
		assertThat("the server should contain vm", server.contains(vm));
	}

	@Test
	public void balancingServerWithOneVm_shouldFillServerWithTenPercent() {
		Server server = getItem(server().withCapacity(10));
		Vm vm = getItem(vm().sizeOf(1));

		balance(serverListWith(server), vmsListWith(vm));

		assertThat(server, hasLoadPercentage(10.0d));
		assertThat("the server should contain vm", server.contains(vm));
	}

	@Test
	public void balancingServerWithEnougCapacity_shouldAddVms() {
		Server server = getItem(server().withCapacity(10));
		Vm vm1 = getItem(vm().sizeOf(1));
		Vm vm2 = getItem(vm().sizeOf(1));

		balance(serverListWith(server), vmsListWith(vm1, vm2));

		assertThat(server, hasVmCountOf(2));
		assertThat("the server should contain vm", server.contains(vm1));
		assertThat("the server should contain vm", server.contains(vm2));
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

	public <T> T getItem(IBuilder<T> builder) {
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
