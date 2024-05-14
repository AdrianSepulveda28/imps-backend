package com.imps.IMPS.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String requestID;
	
	private String userID;
	
	private String header;
	
	private String content;
	
	private Date createdDate;
	
	private Boolean isRead;
	
	private Boolean forAdmin;
	
	public Notification() {}
	
	public Notification (String requestID, String userID, String header, String content, Date createdDate, Boolean forAdmin) {
		this.setRequestID(requestID);
		this.setUserID(userID);
		this.setHeader(header);
		this.setContent(content);
		this.setCreatedDate(createdDate);
		this.setIsRead(false);
		this.setForAdmin(forAdmin);
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getForAdmin() {
		return forAdmin;
	}

	public void setForAdmin(Boolean forAdmin) {
		this.forAdmin = forAdmin;
	}
}
