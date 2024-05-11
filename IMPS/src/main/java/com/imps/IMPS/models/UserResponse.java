package com.imps.IMPS.models;

import java.util.List;

public class UserResponse extends ServerResponse {
	private List<User> results;

	public UserResponse(Boolean status, String message, String serverToken, List<User> users) {
		this.setStatus(status);
		this.setMessage(message);
		this.setServerToken(serverToken);
		this.setUsers(users);
	}
	
	public List<User> getUsers() {
		return results;
	}

	public void setUsers(List<User> users) {
		this.results = users;
	}
}
