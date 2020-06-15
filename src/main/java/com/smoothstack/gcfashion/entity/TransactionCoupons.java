package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_coupons")
public class TransactionCoupons implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179099953060096491L;

	@EmbeddedId
	TransactionCouponsKey id;

	public TransactionCoupons() {
		
	}
	
	public TransactionCoupons(TransactionCouponsKey id) {
		super();
		this.id = id;
	}
	
	public TransactionCouponsKey getId() {
		return id;
	}
	
	public void setId(TransactionCouponsKey id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TransactionCoupons)) {
			return false;
		}
		TransactionCoupons other = (TransactionCoupons) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
