package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.vmsCount() == expectedVmsCount;
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of " + expectedVmsCount);
    }
}
