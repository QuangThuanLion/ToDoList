package com.todolist.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todolist.dto.ApiResponse;
import com.todolist.dto.WorkDTO;
import com.todolist.exception.WorkNotFoundException;
import com.todolist.service.WorkService;
import com.todolist.util.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

/**
 * @author Admin
 *
 */
@RestController(value = "appWorkController")
@RequestMapping("/api/v1")
public class AppWorkController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AppWorkController.class);
	
	@Autowired
	private WorkService workServiceInterface;

	/**
	 * @param pageNumber
	 * @param sortBy
	 * @param sortDir
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@GetMapping(value = "/work", produces = MediaType.APPLICATION_JSON_VALUE, params = {"pageNumber", "sortBy", "sortDir"})
	public ApiResponse listByPage(@RequestParam Integer pageNumber, String sortBy, String sortDir) {
		ApiResponse response = new ApiResponse();
		
		try {
			List<WorkDTO> listWorks = workServiceInterface.getAllWorks(pageNumber, sortBy, sortDir);
			response.setData(listWorks);
			response.setMessage(Constants.MESSAGE_SUCCESS);
			response.setSuccess(true);
		} catch (Exception exception) {
			response.setMessage(exception.getMessage());
			response.setSuccess(false);
		}
		
		return response;
	}
	
	/**
	 * @param workDTO
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@PostMapping(value = "/work", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse saveWork(@RequestBody WorkDTO workDTO) {
		ApiResponse response = new ApiResponse();
		
		try {
			WorkDTO savedWork = workServiceInterface.createWork(workDTO);
			response.setData(savedWork);
			response.setMessage(Constants.MESSAGE_SUCCESS);
			response.setSuccess(true);
		} catch (Exception exception) {
			response.setMessage(exception.getMessage());
			response.setSuccess(false);
		}
		
		return response;
	}
	
	/**
	 * @param workDTO
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@PostMapping(value = "/works", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createWork(@RequestBody WorkDTO workDTO) {
		WorkDTO savedWork = workServiceInterface.createWork(workDTO);
		
		//purpose return in the header, attached location in header
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWork.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}

	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@GetMapping(value = "/work/{id}")
	public ResponseEntity<ApiResponse> findById(@PathVariable(name = "id") Integer id) {
		ApiResponse response = new ApiResponse();
		HttpStatus status = HttpStatus.OK;
		
		try {
			WorkDTO workDTO = workServiceInterface.findById(id);
			response.setData(workDTO);
			response.setMessage(Constants.MESSAGE_SUCCESS);
			response.setSuccess(true);
		} catch (WorkNotFoundException exception) {
			response.setMessage(exception.getMessage());
			response.setSuccess(false);
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<ApiResponse>(response, status);
	}
	
//	/**
//	 * @param id
//	 * @return
//	 */
//	@GetMapping(value = "/work/way3/{id}")
//	public EntityModel<ApiResponse> findByIdWay3(@PathVariable(name = "id") Integer id) {
//		ApiResponse response = new ApiResponse();
//		
//		try {
//			WorkDTO workDTO = workServiceInterface.findById(id);
//			response.setData(workDTO);
//			response.setMessage(Constants.MESSAGE_SUCCESS);
//			response.setSuccess(true);
//		} catch (WorkNotFoundException exception) {
//			response.setMessage(exception.getMessage());
//			response.setSuccess(false);
//		}
//		
//		EntityModel<ApiResponse> model = EntityModel.of(response);
//		
//		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).delete(1));
//		model.add(linkToUsers.withRel("Delete-User"));
//		
//		return model;
//	}
	
	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@GetMapping(value = "/works/{id}")
	public ApiResponse findByIdWay2(@PathVariable(name = "id") Integer id) {
		ApiResponse response = new ApiResponse();
		
		WorkDTO workDTO = workServiceInterface.findById(id);
		response.setData(workDTO);
		response.setMessage(Constants.MESSAGE_SUCCESS);
		response.setSuccess(true);
		
		return response;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Get all Works" , response = ApiResponse.class, tags = "App-Work-Controller")
	@ApiResponses(value = {
			@io.swagger.annotations.ApiResponse(code = 200, message = "Success | OK"),
			@io.swagger.annotations.ApiResponse(code = 401, message = "Not Authorized !!"),
			@io.swagger.annotations.ApiResponse(code = 403, message = "Forbdiden !!"),
			@io.swagger.annotations.ApiResponse(code = 404, message = "Not Found !!!"),
			@io.swagger.annotations.ApiResponse(code = 405, message = "Method Not Allow !!"),
			@io.swagger.annotations.ApiResponse(code = 500, message = "Interval Error")
	})
	@DeleteMapping(value = "/work/{id}")
	public ApiResponse delete(@PathVariable(name = "id") Integer id) {
		final String LOG_STRING = "DELETE /api/v1/work/" + id;
		LOGGER.info("START" + LOG_STRING);
		
		ApiResponse response = new ApiResponse();
		
		try {
			workServiceInterface.delete(id);
			response.setMessage(Constants.MESSAGE_SUCCESS);
			response.setSuccess(true);
		} catch (WorkNotFoundException exception) {
			response.setMessage(exception.getMessage());
			response.setSuccess(false);
		}
		
		LOGGER.info("END" + LOG_STRING);
		return response;
	}
}
