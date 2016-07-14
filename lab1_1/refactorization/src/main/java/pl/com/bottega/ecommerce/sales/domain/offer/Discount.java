package pl.com.bottega.ecommerce.sales.domain.offer;

/**
 * @author Mateusz Wieczorek
 */
public class Discount {

    private Money money;
    private String cause;

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (!cause.equals(discount.cause)) return false;
        if (!money.equals(discount.money)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cause.hashCode();
        result = 31 * result + money.hashCode();
        return result;
    }

}