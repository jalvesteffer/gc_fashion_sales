package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_products")
public class TransactionProducts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179099953060096491L;

	@EmbeddedId
	TransactionProductsKey id;

	public TransactionProducts() {
		
	}
	
	public TransactionProducts(TransactionProductsKey id) {
		super();
		this.id = id;
	}
	
	public TransactionProductsKey getId() {
		return id;
	}
	
	public void setId(TransactionProductsKey id) {
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
		if (!(obj instanceof TransactionProducts)) {
			return false;
		}
		TransactionProducts other = (TransactionProducts) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
