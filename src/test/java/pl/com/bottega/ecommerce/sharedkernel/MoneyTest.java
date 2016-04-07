package pl.com.bottega.ecommerce.sharedkernel;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by Mateusz on 2016-04-07.
 */
public class MoneyTest {

    @Test
    public void multiplyTest() {
        BigDecimal number = new BigDecimal("13.2");
        Money money = new Money(number);
        Money newMoney = money.multiplyBy(651.6f);
        assertThat(newMoney.getDenomination().equals(number.multiply(new BigDecimal("651.6"))), is(true));
    }

}