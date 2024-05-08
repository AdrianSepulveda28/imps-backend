package com.imps.IMPS.models;

import java.util.List;

public class ServerResponse {
	private boolean status;
	private String message;
	private String serverToken;
	private List<User> results;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getServerToken() {
		return serverToken;
	}
	public void setServerToken(String serverToken) {
		this.serverToken = serverToken;
	}
	public List<User> getResults() {
		return results;
	}
	
	public void setResults(List<User> UserList) {
		this.results = UserList;
	}
}
