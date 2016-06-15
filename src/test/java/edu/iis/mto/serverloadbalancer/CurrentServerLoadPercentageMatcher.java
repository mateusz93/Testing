package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class CurrentServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedPercentage;

    public CurrentServerLoadPercentageMatcher(double expectedPercentage) {
        this.expectedPercentage = expectedPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.currentLoadPercentage == expectedPercentage || Math.abs(server.currentLoadPercentage - expectedPercentage) < 0.01d;
    }

    public void describeTo(Description description) {
        description.appendText("server with load percentage equal " + expectedPercentage);
    }
}
