package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoadPercentage;
    private final double EPSILON = 0.01d;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public void describeTo(Description description) {
        description.appendText("a server with load percentage of " + expectedLoadPercentage);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return equalsDouble(server.getCurrentLoadPercentage(), expectedLoadPercentage);
    }

    private boolean equalsDouble(double number1, double number2) {
        return number1 == number2 || Math.abs(number1 - number2) < EPSILON;
    }
}
