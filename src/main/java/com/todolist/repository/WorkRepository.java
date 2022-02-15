package com.todolist.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.entity.Work;

/**
 * @author Admin
 *
 */
@Repository(value = "workRepository")
@Transactional
public interface WorkRepository extends PagingAndSortingRepository<Work, Integer>{

	/**
	 * @param id
	 * @return
	 */
	Long countById(Integer id);
}
