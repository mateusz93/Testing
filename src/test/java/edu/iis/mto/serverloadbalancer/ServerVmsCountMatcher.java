package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.countVms() == expectedVmsCount;
    }

    @Override
    public void describeMismatchSafely(Server server, Description description) {
        description.appendText("a server with vms count of ").appendValue(server.countVms());
    }

    public void describeTo(Description description) {
        description.appendText("a server with vms count of ").appendValue(expectedVmsCount);
    }

    public static ServerVmsCountMatcher hasAVmsCountOf(int expectedVmsCount) {
        return new ServerVmsCountMatcher(expectedVmsCount);
    }
}
