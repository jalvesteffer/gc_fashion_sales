package com.smoothstack.gcfashion.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5025050942490678068L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cat_id")
	private Long catId;
	
	@Column(name = "cat_name")
	private String catName;
	
	@Column(name = "cat_desc")
	private String catDesc;
	
	@OneToMany(mappedBy = "category")
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Subcategory> subcategories;
	
	@OneToMany(mappedBy = "category")
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Product> products;

	/**
	 * @return the catId
	 */
	public Long getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Long catId) {
		this.catId = catId;
	}

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * @return the catDesc
	 */
	public String getCatDesc() {
		return catDesc;
	}

	/**
	 * @param catDesc the catDesc to set
	 */
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	/**
	 * @return the subcategories
	 */
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	/**
	 * @param subcategories the subcategories to set
	 */
	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catDesc == null) ? 0 : catDesc.hashCode());
		result = prime * result + ((catId == null) ? 0 : catId.hashCode());
		result = prime * result + ((catName == null) ? 0 : catName.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((subcategories == null) ? 0 : subcategories.hashCode());
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
		Category other = (Category) obj;
		if (catDesc == null) {
			if (other.catDesc != null)
				return false;
		} else if (!catDesc.equals(other.catDesc))
			return false;
		if (catId == null) {
			if (other.catId != null)
				return false;
		} else if (!catId.equals(other.catId))
			return false;
		if (catName == null) {
			if (other.catName != null)
				return false;
		} else if (!catName.equals(other.catName))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (subcategories == null) {
			if (other.subcategories != null)
				return false;
		} else if (!subcategories.equals(other.subcategories))
			return false;
		return true;
	}

	

	

}
