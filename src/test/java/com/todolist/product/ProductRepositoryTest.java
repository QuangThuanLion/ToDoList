package com.todolist.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.todolist.entity.Product;
import com.todolist.repository.ProductRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 
	 */
	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setName("Kia Morning");
		product.setPrice(2333);
		product.setDescription("Description Kia Morning");
		
		Product product_one = new Product();
		product_one.setName("HuynDai");
		product_one.setPrice(444);
		product_one.setDescription("Description HuynDai");
		
		Product product_two = new Product();
		product_two.setName("Yamaha");
		product_two.setPrice(6666);
		product_two.setDescription("Description Yamaha");
		
		Product product_three = new Product();
		product_three.setName("Lamborigi");
		product_three.setPrice(9999);
		product_three.setDescription("Description Lamborigi");
		
		List<Product> listsList = Arrays.asList(product, product_one, product_two, product_three);
		
		List<Product> savedAll = productRepository.saveAll(listsList);
		
		assertThat(savedAll.size()).isEqualTo(4);
		
		savedAll.forEach(item -> {
			System.out.println(item);
		});
	}
}
