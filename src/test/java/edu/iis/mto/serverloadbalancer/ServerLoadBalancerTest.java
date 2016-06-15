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

	private Matcher<? super Server> hasLoadPercentage(double expectedPercentage) {
		return new CurrentServerLoadPercentageMatcher(expectedPercentage);
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	public Server a(ServerBuilder builder) {
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
