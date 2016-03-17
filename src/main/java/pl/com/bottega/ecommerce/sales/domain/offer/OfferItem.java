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
	private BigDecimal totalCost;
	private String currency;

    public OfferItem(Product product, int quantity) {
		this(product, quantity, null);
	}

	public OfferItem(Product product, int quantity, Discount discount) {
		this.product = product;
		this.quantity = quantity;
        this.discount = discount;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount.getValue() != null)
			discountValue = discountValue.subtract(discount.getValue());

		this.totalCost = product.getPrice().multiply(new BigDecimal(quantity)).subtract(discountValue);
	}

    public Product getProduct() {
        return product;
    }

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public String getTotalCostCurrency() {
		return currency;
	}

	public BigDecimal getDiscount() {
		return discount.getValue();
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
		result = prime * result + ((discount.getValue() == null) ? 0 : discount.getValue().hashCode());
		result = prime * result + ((product.getName() == null) ? 0 : product.getName().hashCode());
		result = prime * result + ((product.getPrice() == null) ? 0 : product.getPrice().hashCode());
		result = prime * result + ((product.getId() == null) ? 0 : product.getId().hashCode());
		result = prime * result + ((product.getType() == null) ? 0 : product.getType().hashCode());
		result = prime * result + quantity;
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		if (discount.getValue() == null) {
			if (other.discount.getValue() != null)
				return false;
		} else if (!discount.getValue().equals(other.discount.getValue()))
			return false;
		if (product.getName() == null) {
			if (other.product.getName() != null)
				return false;
		} else if (!product.getName().equals(other.product.getName()))
			return false;
		if (product.getPrice() == null) {
			if (other.product.getPrice() != null)
				return false;
		} else if (!product.getPrice().equals(other.product.getPrice()))
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
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
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
		if (product.getPrice() == null) {
			if (other.product.getPrice() != null)
				return false;
		} else if (!product.getPrice().equals(other.product.getPrice()))
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

		BigDecimal max, min;
		if (totalCost.compareTo(other.totalCost) > 0) {
			max = totalCost;
			min = other.totalCost;
		} else {
			max = other.totalCost;
			min = totalCost;
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
