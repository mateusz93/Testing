package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of " + expectedLoadPercentage);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.currentLoadPercentage == expectedLoadPercentage || Math.abs(server.currentLoadPercentage - expectedLoadPercentage) < 0.01d;
    }
}
