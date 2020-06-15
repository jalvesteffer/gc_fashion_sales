package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransactionCouponsKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5772758063458400270L;

	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "coupon_id")
	private Long couponId;

	public TransactionCouponsKey() {

	}

	public TransactionCouponsKey(Long transactionId, Long couponId) {
		super();
		this.transactionId = transactionId;
		this.couponId = couponId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getcouponId() {
		return couponId;
	}

	public void setcouponId(Long couponId) {
		this.couponId = couponId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactionId, couponId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TransactionCouponsKey)) {
			return false;
		}
		TransactionCouponsKey other = (TransactionCouponsKey) obj;
		return Objects.equals(transactionId, other.transactionId) && Objects.equals(couponId, other.couponId);
	}
	
}
