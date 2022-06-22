package com.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.dto.ApiResponse;
import com.todolist.dto.ProductRequest;
import com.todolist.entity.Product;
import com.todolist.service.ProductService;
import com.todolist.util.Constants;

/**
 * @author Admin
 *
 */
@RestController(value = "appRestController")
@RequestMapping("/api/v1")
public class AppRestController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getAllProducts() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> products = productService.findAll();
			apiResponse.setData(products);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception e) {
			apiResponse.setMessage(e.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/in", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductsIn(
			@RequestParam(name = "param_one", required = true) String param_one,
			@RequestParam(name = "param_two", required = true) String param_two) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIn = productService.findByByNameProductIn(param_one,param_two);
			apiResponse.setData(productsIn);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param price
	 * @return
	 */
	@GetMapping(value = "/products/gt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductGt(
			@RequestParam(name = "properties.gt", required =  true) Integer price) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsGt = productService.findByProductPriceGreaterThan(price);
			apiResponse.setData(productsGt);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param price
	 * @return
	 */
	@GetMapping(value = "/products/lt", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductLt(
			@RequestParam(name = "properties.lt", required = true) Integer price) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsLt = productService.findByProductPriceLessThan(price);
			apiResponse.setData(productsLt);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param name
	 * @return
	 */
	@GetMapping(value = "/products/like", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductLike(
			@RequestParam(name = "properties.like", required = true) String name) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsLike = productService.findByProductLike(name);
			apiResponse.setData(productsLike);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param param_one
	 * @param param_two
	 * @return
	 */
	@GetMapping(value = "/products/between", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductBetween(
			@RequestParam(name = "properties.param_one", required = true) Integer param_one,
			@RequestParam(name = "properties.param_two", required = true) Integer param_two) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsBetween = productService.findByProductBetween(param_one, param_two);
			apiResponse.setData(productsBetween);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/is_null", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductIsNull() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsNull = productService.findByProductIsNull();
			apiResponse.setData(productsIsNull);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/is_not_null", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductIsNotNull() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsNotNull = productService.findByProductIsNotNull();
			apiResponse.setData(productsIsNotNull);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param isNull
	 * @param like
	 * @return
	 */
	@GetMapping(value = "/products/chain_expression", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductChainExpression(
			@RequestParam(name = "properties.like", required = true) String like) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsChainExpression = productService.findByProductIsChainExpression(like);
			apiResponse.setData(productsIsChainExpression);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param productPrice
	 * @param name
	 * @return
	 */
	@GetMapping(value = "/products/chain_expression/gt/like", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductChainExpressionGtAndLike(
			@RequestParam(name = "properties.gt", required = true) Integer productPrice,
			@RequestParam(name = "properties.like", required = true) String name) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsChainExpressionGtAndLike = productService.findByProductIsChainExpression(productPrice, name);
			apiResponse.setData(productsIsChainExpressionGtAndLike);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/ordering", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductOrdering() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsOrdering = productService.findByProductIsOrdering();
			apiResponse.setData(productsIsOrdering);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/groupBy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getproductGroupBy() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			List<Product> productsIsGroupBy = productService.findByProductIsGroupBy();
			apiResponse.setData(productsIsGroupBy);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/aggregates/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductAggregatesCount() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			Long productsIsAggregates = productService.findByProductIsAggregatesCount();
			apiResponse.setData(productsIsAggregates);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @return
	 */
	@GetMapping(value = "/products/aggregates/avg", produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse getProductAggregatesAvg() {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			Long productsIsAggregatesAvg = productService.findByProductIsAggregatesAvg();
			apiResponse.setData(productsIsAggregatesAvg);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param productRequest
	 * @return
	 */
	@PutMapping(value = "/products", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse updateProducts(@RequestBody ProductRequest productRequest) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			productRequest = productService.updateProduct(productRequest);
			apiResponse.setData(productRequest);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return apiResponse;
	}
	
	/**
	 * @param productId
	 * @return
	 */
	@DeleteMapping(value = "/products/{productId}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable(name = "productId") Integer productId) {
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			productService.deleteProductById(productId);
			apiResponse.setSuccess(true);
			apiResponse.setMessage(Constants.MESSAGE_SUCCESS);
		} catch (Exception ex) {
			apiResponse.setMessage(ex.getMessage());
		}
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK); 
	}
}
