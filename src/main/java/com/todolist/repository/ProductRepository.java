package com.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.entity.Product;

/**
 * @author admin
 *
 */
@Repository("ProductRepository")
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
