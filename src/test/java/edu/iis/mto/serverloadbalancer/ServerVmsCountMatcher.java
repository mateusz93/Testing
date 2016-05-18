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
        return false;
    }

    @Override
    public void describeMismatchSafely(Server server, Description description) {
        description.appendText("a server with vmos count of ").appendValue(expectedVmsCount);
    }

    public void describeTo(Description description) {
        description.appendText("a server with vmos count of ").appendValue(expectedVmsCount);
    }
}
