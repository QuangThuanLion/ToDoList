package com.todolist.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todolist.entity.Product;
import com.todolist.repository.ProductRepository;
import com.todolist.service.implement.ProductServiceImpl;

/**
 * @author admin
 *
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	
	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private EntityManagerFactory entityManagerFactory;
	
	@MockBean
	private EntityManager entityManager;
	
	@MockBean
	private CriteriaBuilder criteriaBuilder;
	
	@MockBean
	private CriteriaQuery<Product> criteriaQuery;
	
	@MockBean
	private Root<Product> root;
	
	@MockBean
	private TypedQuery<Product> typedQuery;
	
	/**
	 * 
	 */
	@Test
	public void testGetAll() {
		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(Product.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(Product.class)).thenReturn(root);
		when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
		
		List<Product> products = productServiceImpl.findAll();
		
		assertThat(products.size()).isEqualTo(4);
		
		products.forEach(product -> {
			System.out.println(product);
		});
	}
	
	/**
	 * 
	 */
	@Test
	public void createdProduct() {
		Product product = new Product();
		product.setName("Java Script");
		product.setPrice(9999);
		product.setDescription("description javascript");
		productServiceImpl.savedProduct(product);
	}
}
