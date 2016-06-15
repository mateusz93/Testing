package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class CurrentServerCountOfVmsMatcher extends TypeSafeMatcher<Server> {

    private int expectedCount;

    public CurrentServerCountOfVmsMatcher(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    public void describeTo(Description description) {
        description.appendText("server should containt vms count of" + expectedCount);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.getVmsCount() == expectedCount;
    }
}
