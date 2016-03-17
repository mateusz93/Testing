package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Mateusz Wieczorek
 */
public class Product {

    private String productId;
    private BigDecimal productPrice;
    private String productName;
    private Date productSnapshotDate;
    private String productType;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public void setProductSnapshotDate(Date productSnapshotDate) {
        this.productSnapshotDate = productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!productId.equals(product.productId)) return false;
        if (!productName.equals(product.productName)) return false;
        if (!productPrice.equals(product.productPrice)) return false;
        if (!productSnapshotDate.equals(product.productSnapshotDate)) return false;
        if (!productType.equals(product.productType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + productPrice.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + productSnapshotDate.hashCode();
        result = 31 * result + productType.hashCode();
        return result;
    }
}
