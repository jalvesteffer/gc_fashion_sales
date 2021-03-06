package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2253090887578794285L;

	@Id
	@Column(name = "coupon_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "product_type")
	private String productType;

	@Column(name = "applies_to")
	private String appliesTo;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "coupon_desc")
	private String couponDesc;
	
	@ManyToMany(mappedBy = "coupons")
	@JsonBackReference(value="couponTransactions")
	private List<Transaction> transactions;

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAppliesTo() {
		return appliesTo;
	}

	public void setAppliesTo(String appliesTo) {
		this.appliesTo = appliesTo;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appliesTo == null) ? 0 : appliesTo.hashCode());
		result = prime * result + ((couponDesc == null) ? 0 : couponDesc.hashCode());
		result = prime * result + ((couponId == null) ? 0 : couponId.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
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
		Coupon other = (Coupon) obj;
		if (appliesTo == null) {
			if (other.appliesTo != null)
				return false;
		} else if (!appliesTo.equals(other.appliesTo))
			return false;
		if (couponDesc == null) {
			if (other.couponDesc != null)
				return false;
		} else if (!couponDesc.equals(other.couponDesc))
			return false;
		if (couponId == null) {
			if (other.couponId != null)
				return false;
		} else if (!couponId.equals(other.couponId))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}

	
	
}
