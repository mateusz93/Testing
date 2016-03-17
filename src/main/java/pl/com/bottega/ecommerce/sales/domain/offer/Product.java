package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

/**
 * @author Mateusz Wieczorek
 */
public class Product {

    private String id;
    private Money money;
    private String name;
    private Date snapshotDate;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(Date snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!money.equals(product.money)) return false;
        if (!name.equals(product.name)) return false;
        if (!snapshotDate.equals(product.snapshotDate)) return false;
        if (!type.equals(product.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + money.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + snapshotDate.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
