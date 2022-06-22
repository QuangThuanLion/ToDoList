package com.todolist.service;

import java.util.List;

import com.todolist.dto.WorkDTO;
import com.todolist.exception.WorkNotFoundException;

/**
 * @author Admin
 *
 */
public interface WorkService {

	/**
	 * @return
	 */
	List<WorkDTO> getAllWorks(Integer pageNumber, String sortBy, String sortDir);
	
	/**
	 * @param workDTO
	 * @return
	 */
	WorkDTO createWork(WorkDTO workDTO);
	
	/**
	 * @param id
	 * @return
	 * @throws WorkNotFoundException 
	 */
	WorkDTO findById(Integer id) throws WorkNotFoundException;
	
	/**
	 * @param id
	 * @throws WorkNotFoundException 
	 */
	void delete(Integer id) throws WorkNotFoundException;
}
