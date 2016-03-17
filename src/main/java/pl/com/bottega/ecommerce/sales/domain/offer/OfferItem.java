/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class OfferItem {

    private Product product;
    private Discount discount;
	private int quantity;
	private Money money;

    public OfferItem(Product product, int quantity) {
		this(product, quantity, null);
	}

	public OfferItem(Product product, int quantity, Discount discount) {
		this.product = product;
		this.quantity = quantity;
        this.discount = discount;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount.getMoney().getValue() != null)
			discountValue = discountValue.subtract(discount.getMoney().getValue());

		this.money.setValue(product.getMoney().getValue().multiply(new BigDecimal(quantity)).subtract(discountValue));
	}

    public Product getProduct() {
        return product;
    }

	public BigDecimal getTotalCost() {
		return money.getValue();
	}

	public String getTotalCostCurrency() {
		return money.getCurrency();
	}

	public BigDecimal getDiscount() {
		return discount.getMoney().getValue();
	}

	public String getDiscountCause() {
		return discount.getCause();
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount.getMoney().getValue() == null) ? 0 : discount.getMoney().getValue().hashCode());
		result = prime * result + ((product.getName() == null) ? 0 : product.getName().hashCode());
		result = prime * result + ((product.getMoney().getValue() == null) ? 0 : product.getMoney().getValue().hashCode());
		result = prime * result + ((product.getId() == null) ? 0 : product.getId().hashCode());
		result = prime * result + ((product.getType() == null) ? 0 : product.getType().hashCode());
		result = prime * result + quantity;
		result = prime * result + ((money.getValue() == null) ? 0 : money.getValue().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferItem other = (OfferItem) obj;
		if (discount.getMoney().getValue() == null) {
			if (other.discount.getMoney().getValue() != null)
				return false;
		} else if (!discount.getMoney().getValue().equals(other.discount.getMoney().getValue()))
			return false;
		if (product.getName() == null) {
			if (other.product.getName() != null)
				return false;
		} else if (!product.getName().equals(other.product.getName()))
			return false;
		if (product.getMoney().getValue() == null) {
			if (other.product.getMoney().getValue() != null)
				return false;
		} else if (!product.getMoney().getValue().equals(other.product.getMoney().getValue()))
			return false;
		if (product.getId() == null) {
			if (other.product.getId() != null)
				return false;
		} else if (!product.getId().equals(other.product.getId()))
			return false;
		if (product.getType() != other.product.getType())
			return false;
		if (quantity != other.quantity)
			return false;
		if (money.getValue() == null) {
			if (other.money.getValue() != null)
				return false;
		} else if (!money.getValue().equals(other.money.getValue()))
			return false;
		return true;
	}

	/**
	 * 
	 * @param other
	 * @param delta
	 *            acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (product.getName() == null) {
			if (other.product.getName() != null)
				return false;
		} else if (!product.getName().equals(other.product.getName()))
			return false;
		if (product.getMoney().getValue() == null) {
			if (other.product.getMoney().getValue() != null)
				return false;
		} else if (!product.getMoney().getValue().equals(other.product.getMoney().getValue()))
			return false;
		if (product.getId() == null) {
			if (other.product.getId() != null)
				return false;
		} else if (!product.getId().equals(other.product.getId()))
			return false;
		if (product.getType() != other.product.getType())
			return false;

		if (quantity != other.quantity)
			return false;

		BigDecimal max = computeMax(money, other);
        BigDecimal min = computeMin(money, other);

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

    private BigDecimal computeMax(Money money, OfferItem other) {
        if (money.getValue().compareTo(other.money.getValue()) > 0) {
            return money.getValue();
        }
        return other.money.getValue();
    }

    private BigDecimal computeMin(Money money, OfferItem other) {
        if (money.getValue().compareTo(other.money.getValue()) > 0) {
            return other.money.getValue();
        }
        return money.getValue();
    }

}
