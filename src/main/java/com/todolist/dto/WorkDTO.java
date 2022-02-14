package com.todolist.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Admin
 *
 */
public class WorkDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String workName;
	private Date startingDate;
	private Date endingDate;
	private String status;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the workName
	 */
	public String getWorkName() {
		return workName;
	}
	/**
	 * @param workName the workName to set
	 */
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	/**
	 * @return the startingDate
	 */
	public Date getStartingDate() {
		return startingDate;
	}
	/**
	 * @param startingDate the startingDate to set
	 */
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	/**
	 * @return the endingDate
	 */
	public Date getEndingDate() {
		return endingDate;
	}
	/**
	 * @param endingDate the endingDate to set
	 */
	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
