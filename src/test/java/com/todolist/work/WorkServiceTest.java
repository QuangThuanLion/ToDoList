package com.todolist.work;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todolist.exception.WorkNotFoundException;
import com.todolist.repository.WorkRepository;
import com.todolist.service.implement.WorkServiceImpl;

/**
 * @author Admin
 *
 */
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class WorkServiceTest {

	@MockBean
	private WorkRepository workRepository;
	
	@InjectMocks
	private WorkServiceImpl workServiceImpl;
	
	/**
	 * 
	 */
	@Test
	public void testDeleteWorkWithIdNotInTable() {
		Integer id = 333;
		Long countById = 0L;
		
		Mockito.when(workRepository.countById(id)).thenReturn(countById);
		
		assertThrows(WorkNotFoundException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				workServiceImpl.delete(id);
			}
		});
	}
	
	/**
	 * @throws WorkNotFoundException 
	 * 
	 */
	@Test
	public void testDeleteWorkWithIdInTable() throws WorkNotFoundException {
		Integer id = 2;
		Long coundById = 1L;
		
		Mockito.when(workRepository.countById(id)).thenReturn(coundById);
		
		workServiceImpl.delete(id);
	}
}
