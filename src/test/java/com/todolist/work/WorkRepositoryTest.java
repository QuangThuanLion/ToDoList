package com.todolist.work;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.todolist.entity.Work;
import com.todolist.entity.WorkStatus;
import com.todolist.repository.WorkRepository;

/**
 * @author Admin
 *
 */
@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class WorkRepositoryTest {

	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	/**
	 * 
	 */
	@Test
	public void testCreateWork() {
		Work work = new Work();
		
		work.setWorkName("Go to school");
		
		Date startingDate = new Date();
		work.setStartingDate(startingDate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startingDate);
		calendar.add(Calendar.DATE, 10);
		Date endingDate = calendar.getTime();
		
		work.setEndingDate(endingDate);
		work.setStatus(WorkStatus.COMPLETE);
		
		Work savedWork = workRepository.save(work);
		
		Mockito.verify(workRepository).save(work);
		assertThat(savedWork.getId()).isGreaterThan(0);
		assertThat(savedWork).isNotNull();
	}
	
	/**
	 * 
	 */
	@Test
	public void testListAllWorks() {
		Iterable<Work> findAll = workRepository.findAll();
		findAll.forEach(System.out::println);
		
		Mockito.verify(workRepository).findAll();
		assertThat(findAll).size().isGreaterThan(1);
		assertThat(findAll).isNotNull();
	}
	
	/**
	 * 
	 */
	@Test
	public void testFindWorkById() {
		Integer workId = 1;
		Work work = workRepository.findById(workId).get();
		
		System.out.println(work);
		
		assertThat(work.getId()).isEqualTo(workId);
		assertThat(work).isNotNull();
	}
	
	/**
	 * 
	 */
	@Test
	public void testUpdateWork() {
		Integer workId = 2;
		Work work = workRepository.findById(workId).get();
		
		work.setStatus(WorkStatus.COMPLETE);
		work.setWorkName("Eating breafast");
		workRepository.save(work);
		
		Work updateWork = testEntityManager.find(Work.class, workId);
		
		assertThat(updateWork.getStatus()).isEqualTo(WorkStatus.COMPLETE);
		assertThat(updateWork.getWorkName()).isEqualTo("Eating breafast");
	}
	
	/**
	 * 
	 */
	@Test
	public void testDeleteWork() {
		Integer workId = 3;
		workRepository.deleteById(workId);
		
		Optional<Work> findById = workRepository.findById(workId);
		
		assertThat(!findById.isPresent());
	}
}
