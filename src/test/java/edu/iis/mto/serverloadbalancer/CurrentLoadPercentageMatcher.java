package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 18.05.16.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoadPercentage;
    private double EPSILON = 0.01d;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return areEqual(expectedLoadPercentage, server.currentLoadPercentage);
    }

    @Override
    protected void describeMismatchSafely(Server server, Description description) {
        description.appendText("a server with load percentage of ").appendValue(server.currentLoadPercentage);
    }

    private boolean areEqual(double number1, double number2) {
        return number1 == number2 || Math.abs(number1 - number2) < EPSILON;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }
}
