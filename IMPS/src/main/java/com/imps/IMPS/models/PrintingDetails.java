package com.imps.IMPS.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrintingDetails {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String userID;
	
	private String requestID;
	
	private String fileName;
	
	private String description;
	
	private Integer noOfCopies;
	
	private String color;
	
	private String paperSize;
	
	private String bindType;
	
	private LocalDate requestDate;
	
	private LocalDateTime requestTime;
	
	private LocalDate useDate;
	
	private String requesterName;
	
	private String requesterEmail;
	
	private String requesterNumber;
	
	private String department;
	
	private String comment;
	
	public PrintingDetails (String filename, String description, Integer noOfCopies, String color, String paperSize,
			String bindType, LocalDate requestDate, LocalDateTime requestTime, LocalDate useDate, String name, String email,
			String number, String department, String comment){
		this.setFileName(filename);
		this.setDescription(description);
		this.setNoOfCopies(noOfCopies);
		this.setColor(color);
		this.setPaperSize(paperSize);
		this.setBindType(bindType);
		this.setRequestDate(requestDate);
		this.setRequestTime(requestTime);
		this.setUseDate(useDate);
		this.setRequesterName(name);
		this.setRequesterEmail(email);
		this.setRequesterNumber(number);
		this.setDepartment(department);
		this.setComment(comment);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPaperSize() {
		return paperSize;
	}

	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}

	public String getBindType() {
		return bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getUseDate() {
		return useDate;
	}

	public void setUseDate(LocalDate useDate) {
		this.useDate = useDate;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getRequesterEmail() {
		return requesterEmail;
	}

	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}

	public String getRequesterNumber() {
		return requesterNumber;
	}

	public void setRequesterNumber(String requesterNumber) {
		this.requesterNumber = requesterNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(LocalDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	

}
