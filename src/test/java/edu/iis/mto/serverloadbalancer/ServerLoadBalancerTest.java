package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

}
