package com.todolist.work;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.dto.WorkDTO;
import com.todolist.entity.Work;
import com.todolist.entity.WorkStatus;
import com.todolist.repository.WorkRepository;

/**
 * @author Admin
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class WorkRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private WorkRepository workRepository;
	
	/**
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 * 
	 */
	@Test
	public void testCreateWork() throws JsonProcessingException, Exception {
		String requestURL = "/api/v1/work";
		
		WorkDTO workDTO = new WorkDTO();
		workDTO.setWorkName("plaing football");
		workDTO.setStartingDate(new Date());
		workDTO.setEndingDate(new Date());
		workDTO.setStatus("PLANNING");
		
		MvcResult mvcResult = mockMvc.perform(post(requestURL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(workDTO)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		JSONObject data = jsonObject.getJSONObject("data");
		Integer id = data.getInt("id");
		
		System.out.println("Id " + id);
		
		Optional<Work> findById = workRepository.findById(id);
		
		assertThat(findById.isPresent());
		assertThat(findById).isNotEmpty();
		assertThat(findById.get().getId()).isEqualTo(id);
	}
	
	/**
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 * 
	 */
	@Test
	public void testUpdateWork() throws JsonProcessingException, Exception {
		String requestURL = "/api/v1/work";
		Integer id = 6;
		Work work = workRepository.findById(id).get();
		
		WorkDTO workDTO = new WorkDTO();
		workDTO.setId(work.getId());
		workDTO.setWorkName("working in the office");
		workDTO.setStartingDate(work.getStartingDate());
		workDTO.setEndingDate(work.getEndingDate());
		workDTO.setStatus("PLANNING");
		
		MvcResult resultMvc = mockMvc.perform(post(requestURL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(workDTO)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		
		String response = resultMvc.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		JSONObject data = jsonObject.getJSONObject("data");
		
		Integer idResponse = data.getInt("id");
		Work updatedWork = workRepository.findById(idResponse).get();
		
		assertThat(updatedWork.getId()).isEqualTo(id);
		assertThat(updatedWork.getWorkName()).isEqualTo("working in the office");
		assertThat(updatedWork.getStatus()).isEqualTo(WorkStatus.PLANNING);
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testDeleteWorkNotInTable() throws Exception {
		Integer id = 333;
		String requestURL = "/api/v1/work/" + id;
		
		MvcResult mvcResult = mockMvc.perform(delete(requestURL))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		String message = jsonObject.getString("message");
		boolean success = jsonObject.getBoolean("success");
		
		System.out.println(message);
		
		assertThat(message).containsIgnoringCase("Could not find");
		assertThat(success).isFalse();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testDeleteWorkInTable() throws Exception {
		Integer id = 10;
		String requestURL = "/api/v1/work/" + id;
		
		MvcResult mvcResult = mockMvc.perform(delete(requestURL))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		String message = jsonObject.getString("message");
		boolean success = jsonObject.getBoolean("success");
		
		System.out.println(message);
		
		assertThat(message).isEqualTo("success");
		assertThat(success).isTrue();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testListByPageWithSortByWorkNameAndSortDirASC() throws Exception {
		Integer pageNumber = 2;
		String sortBy = "workName";
		String sortDir = "asc";
		
		String requestURL = "/api/v1/work/?pageNumber=" + pageNumber + "&sortBy=" + sortBy + "&sortDir=" + sortDir;
	
		MvcResult mvcResult = mockMvc.perform(get(requestURL))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		WorkDTO[] works = objectMapper.readValue(jsonObject.getString("data"), WorkDTO[].class);
		
		for (WorkDTO workDTO : works) {
			System.out.println(workDTO);
		}
		
		assertThat(works).hasSizeGreaterThan(0);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testListByPageWithSortByStatusAndSortDirDESC() throws Exception {
		Integer pageNumber = 1;
		String sortBy = "status";
		String sortDir = "desc";
		
		String requestURL = "/api/v1/work/?pageNumber=" + pageNumber + "&sortBy=" + sortBy + "&sortDir=" + sortDir;
		
		MvcResult mvcResult = mockMvc.perform(get(requestURL))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(response);
		WorkDTO[] works = objectMapper.readValue(jsonObject.getString("data"), WorkDTO[].class);
		
		for (WorkDTO workDTO : works) {
			System.out.println(workDTO);
		}
		
		assertThat(works).hasSizeGreaterThan(0);
	}
}
