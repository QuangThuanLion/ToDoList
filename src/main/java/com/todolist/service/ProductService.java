package com.todolist.service;

import java.util.List;

import com.todolist.dto.ProductRequest;
import com.todolist.entity.Product;

/**
 * @author admin
 *
 */
public interface ProductService {

	/**
	 * @param product
	 * @return
	 */
	Product createProduct(Product product);
	
	/**
	 * @return
	 */
	List<Product> findAll();
	
	/**
	 * @param product
	 */
	void savedProduct(Product product);
	
	/**
	 * @return
	 */
	List<Product> findByByNameProductIn(String... params);

	/**
	 * @param price
	 * @return
	 */
	List<Product> findByProductPriceGreaterThan(Integer price);

	/**
	 * @param price
	 * @return
	 */
	List<Product> findByProductPriceLessThan(Integer price);

	/**
	 * @param name
	 * @return
	 */
	List<Product> findByProductLike(String name);

	/**
	 * @param params
	 * @return
	 */
	List<Product> findByProductBetween(Integer... params);

	/**
	 * @return
	 */
	List<Product> findByProductIsNull();

	/**
	 * 
	 */
	List<Product> findByProductIsNotNull();

	/**
	 * @param like
	 * @return
	 */
	List<Product> findByProductIsChainExpression(String like);

	/**
	 * @param productPrice
	 * @param name
	 * @return
	 */
	List<Product> findByProductIsChainExpression(Integer productPrice, String name);

	/**
	 * @return
	 */
	List<Product> findByProductIsOrdering();

	/**
	 * @return
	 */
	List<Product> findByProductIsGroupBy();

	/**
	 * @return
	 */
	Long findByProductIsAggregatesCount();

	/**
	 * @return
	 */
	Long findByProductIsAggregatesAvg();

	/**
	 * @param productRequest
	 * @return
	 * @throws Exception 
	 */
	ProductRequest updateProduct(ProductRequest productRequest) throws Exception;

	/**
	 * @param productId
	 * @throws Exception 
	 */
	void deleteProductById(Integer productId) throws Exception;
}
