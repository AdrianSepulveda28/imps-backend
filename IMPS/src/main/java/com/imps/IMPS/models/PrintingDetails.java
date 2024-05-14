package com.imps.IMPS.models;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	private String fileType;
	
	private String fileName;
	
	private String description;
	
	private Integer noOfCopies;
	
	private Boolean color;
	
	private Boolean stapled;
	
	private Boolean giveExam;
	
	private String paperSize;
	
	private String bindType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date requestDate;
	
	private LocalDateTime requestTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date useDate;
	
	private String requesterName;
	
	private String requesterEmail;
	
	private String requesterNumber;
	
	private String department;
	
	private String downloadURL;
	
	public PrintingDetails() {}

	
	public PrintingDetails (String userID, String requestID, String filename, String filetype, String description, Integer noOfCopies, Boolean color, Boolean stapled, Boolean giveExam, String paperSize,
			String bindType, Date requestDate, LocalDateTime requestTime, Date useDate, String name, String email,
			String number, String department, String downloadURL){
		this.setUserID(userID);
		this.setRequestID(requestID);
		this.setFileName(filename);
		this.setFileType(filetype);
		this.setDescription(description);
		this.setNoOfCopies(noOfCopies);
		this.setColor(color);
		this.setStapled(stapled);
		this.setGiveExam(giveExam);
		this.setPaperSize(paperSize);
		this.setBindType(bindType);
		this.setRequestDate(requestDate);
		this.setRequestTime(requestTime);
		this.setUseDate(useDate);
		this.setRequesterName(name);
		this.setRequesterEmail(email);
		this.setRequesterNumber(number);
		this.setDepartment(department);
		this.setDownloadURL(downloadURL);
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

	public Boolean getColor() {
		return color;
	}

	public void setColor(Boolean color) {
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

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Boolean getStapled() {
		return stapled;
	}

	public void setStapled(Boolean stapled) {
		this.stapled = stapled;
	}

	public Boolean getGiveExam() {
		return giveExam;
	}

	public void setGiveExam(Boolean giveExam) {
		this.giveExam = giveExam;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}
	

}
