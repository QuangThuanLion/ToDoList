package com.todolist.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author admin
 *
 */
@Entity
@Table(name = "products")
@NamedQueries(
		{
			@NamedQuery(name = Product.Q_FIND_ALL, query = "SELECT p FROM Product p"),
			@NamedQuery(name = Product.Q_FIND_BY_ID, query = "SELECT p FROM Product p WHERE p.id = : id")
		}
)
public class Product implements Serializable {
	
	public static final String Q_FIND_ALL = "Product.findAll";
	public static final String Q_FIND_BY_ID = "Product.findById";
	
	public static final String PROP_ID = "id";
	public static final String PROP_NAME = "product_name";
	public static final String PROP_PRICE = "product_price";
	public static final String PROP_DESCRIPTION = "product_description";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4767401290408817540L;
	
	@Id
	@Column(name = "id", 
			updatable = false, 
			insertable = true, 
			nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "product_name",
			nullable = false,
			updatable = true,
			insertable = true,
			length = 50)
	@Basic(optional = false)
	private String name;
	
	@Column(name = "product_price",
			nullable = false,
			insertable = true,
			updatable = true)
	@Basic(optional = false)
	private Integer price;
	
	@Column(name = "product_description",
			updatable = true,
			insertable = true,
			nullable = true,
			length = 100)
	private String description;

	public Product() {
		
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 */
	public Product(int id, String name, String description, long price) {
		this.id = id;
		this.name = name;
		this.price = (int) price;
		this.description = description;
	}
	
	/**
	 * @param name
	 * @param price
	 * @param description
	 */
	public Product(String name, String description, long price) {
		this.name = name;
		this.price = (int) price;
		this.description = description;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
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
	 *
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}
}
