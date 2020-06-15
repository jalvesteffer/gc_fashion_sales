package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransactionProductsKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5772758063458400270L;

	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "product_id")
	private Long productId;

	public TransactionProductsKey() {

	}

	public TransactionProductsKey(Long transactionId, Long productId) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactionId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TransactionProductsKey)) {
			return false;
		}
		TransactionProductsKey other = (TransactionProductsKey) obj;
		return Objects.equals(transactionId, other.transactionId) && Objects.equals(productId, other.productId);
	}
	
}
