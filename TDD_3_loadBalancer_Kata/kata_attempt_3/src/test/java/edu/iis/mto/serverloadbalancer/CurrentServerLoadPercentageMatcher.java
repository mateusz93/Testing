package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @Author Mateusz Wieczorek, 15.06.16.
 */
public class CurrentServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {

    private double expectedPercentage;
    private final double EPSILON = 0.01d;

    public CurrentServerLoadPercentageMatcher(double expectedPercentage) {
        this.expectedPercentage = expectedPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return equalsDoubles(server.currentLoadPercentage, expectedPercentage);
    }

    public void describeTo(Description description) {
        description.appendText("server with load percentage equal " + expectedPercentage);
    }

    private boolean equalsDoubles(double number1, double number2) {
        return number1 == number2 || Math.abs(number1 - number2) < EPSILON;
    }
}
