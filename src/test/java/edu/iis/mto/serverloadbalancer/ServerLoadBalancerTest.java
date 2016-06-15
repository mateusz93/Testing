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
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server server = getItem(server().withCapacity(1));

		balance(aListOfServersWith(server), anEmptyListOfVms());

		assertThat(server, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacityAndOneVms_fillsTheServerWithTheVm() {
		Server server = getItem(server().withCapacity(1));
		Vm vm = getItem(vm().ofSize(1));

		balance(aListOfServersWith(server), anListOfVmsWith(vm));

		assertThat(server, hasLoadPercentageOf(100.0d));
		assertThat("the server should contain vm", server.contains(vm));
	}

	@Test
	public void balancingOneServerWithTenSlotCapacityAndOneVms_fillsTheServerWithTheVm() {
		Server server = getItem(server().withCapacity(10));
		Vm vm = getItem(vm().ofSize(1));

		balance(aListOfServersWith(server), anListOfVmsWith(vm));

		assertThat(server, hasLoadPercentageOf(10.0d));
		assertThat("the server should contain vm", server.contains(vm));
	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVm() {
		Server server = getItem(server().withCapacity(10));
		Vm vm1 = getItem(vm().ofSize(1));
		Vm vm2 = getItem(vm().ofSize(1));

		balance(aListOfServersWith(server), anListOfVmsWith(vm1, vm2));

		assertThat(server, hasVmsCountOf(2));
		assertThat("the server should contain vm", server.contains(vm1));
		assertThat("the server should contain vm", server.contains(vm2));
	}

	private Matcher<? super Server> hasVmsCountOf(int expectedVmsCount) {
		return new ServerVmsCountMatcher(expectedVmsCount);
	}

	private <T> T getItem(IBuilder<T> builder) {
		return builder.build();
	}

	private Vm[] anListOfVmsWith(Vm... vm) {
		return vm;
	}

	private Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[]{server};
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private VmBuilder vm() {
		return new VmBuilder();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
