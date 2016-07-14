package edu.iis.mto.time;

import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @Author Mateusz Wieczorek, 19.04.16.
 */
public class OrderTest {

    private long invalidDate = DateTimeUtils.currentTimeMillis() + Duration.standardDays(10).getMillis();

    @Test(expected = OrderExpiredException.class)
    public void shouldThrowOrderExpiredException() {
        DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis());
        Order order = new Order();
        order.submit();
        DateTimeUtils.setCurrentMillisFixed(invalidDate);
        order.confirm();
    }

    @Test
    public void shouldOrderStateBeSubmitted() {
        DateTimeUtils.setCurrentMillisFixed(System.currentTimeMillis());
        Order order = new Order();
        order.submit();
        order.confirm();
        assertThat(order.getOrderState(), is(Order.State.SUBMITTED));
    }

}