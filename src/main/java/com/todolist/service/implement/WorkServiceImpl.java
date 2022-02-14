package com.todolist.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.dto.WorkDTO;
import com.todolist.entity.Work;
import com.todolist.entity.WorkStatus;
import com.todolist.exception.WorkNotFoundException;
import com.todolist.repository.WorkRepository;
import com.todolist.service.WorkServiceInterface;

/**
 * @author Admin
 *
 */
@Service(value = "workServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class WorkServiceImpl implements WorkServiceInterface {

	private static final int WORK_PER_PAGE = 3;
	
	@Autowired
	private WorkRepository workRepository;
	
	/**
	 * @param pageNumber
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	@Override
	public List<WorkDTO> getAllWorks(Integer pageNumber, String sortBy, String sortDir) {
		List<WorkDTO> result = new ArrayList<WorkDTO>();
		
		Sort sort = Sort.by(sortBy);
		
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber - 1, WORK_PER_PAGE, sort);
		
		Page<Work> findAll = workRepository.findAll(pageable);
		
		List<Work> content = findAll.getContent();
		
		content.forEach(work -> {
			WorkDTO workDTO = this.convertWorkEntityToDTO(work);
			result.add(workDTO);
		});
		
		return result;
	}

	/**
	 * @param workDTO
	 */
	@Override
	public WorkDTO createWork(WorkDTO workDTO) {
		Work work = new Work();
		
		if (!Objects.isNull(workDTO.getId())) {
			work.setId(workDTO.getId());
		}
		work.setWorkName(workDTO.getWorkName());
		work.setStartingDate(workDTO.getStartingDate());
		work.setEndingDate(workDTO.getEndingDate());
		work.setStatus(WorkStatus.valueOf(workDTO.getStatus()));
		
		Work savedWork = workRepository.save(work);
		workDTO = this.convertWorkEntityToDTO(savedWork);
		
		return workDTO;
	}

	/**
	 * @param savedWork
	 * @return 
	 */
	private WorkDTO convertWorkEntityToDTO(Work savedWork) {
		WorkDTO workDTO = new WorkDTO();
		
		workDTO.setId(savedWork.getId());
		workDTO.setWorkName(savedWork.getWorkName());
		workDTO.setStartingDate(savedWork.getStartingDate());
		workDTO.setEndingDate(savedWork.getEndingDate());
		workDTO.setStatus(savedWork.getStatus().toString());
		
		return workDTO;
	}

	/**
	 * @param id
	 * @throws WorkNotFoundException 
	 *
	 */
	@Override
	public void delete(Integer id) throws WorkNotFoundException {
		Long countById = workRepository.countById(id);
		
		if (Objects.isNull(countById) || countById == 0) {
			throw new WorkNotFoundException("Could not find any work with ID " + id);
		}
		
		workRepository.deleteById(id);
	}
}
