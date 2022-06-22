package com.todolist.dto;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class ProductRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293286653511164917L;
	
	private Integer id;
	private String productName;
	private Integer productPrice;
	private String description;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productPrice
	 */
	public Integer getProductPrice() {
		return productPrice;
	}
	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return
	 */
	public Object checkNullProductRequest() {
		if (this.id == null 
				|| this.productName == null 
				|| this.productPrice == null 
				|| this.description == null) {
			return null;
		}
		
		return this;
	}
}
