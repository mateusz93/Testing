package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

/**
 * @author Mateusz Wieczorek
 */
public class Discount {

    private String cause;
    private BigDecimal value;
    private String currency;

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (!cause.equals(discount.cause)) return false;
        if (!currency.equals(discount.currency)) return false;
        if (!value.equals(discount.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cause.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

}