package com.imps.IMPS.models;

import java.util.List;

public class CommentResponse extends ServerResponse {
	private List<Comment> results;
	
	public CommentResponse(Boolean status, String message, String serverToken, List<Comment> comments) {
		this.setStatus(status);
		this.setMessage(message);
		this.setServerToken(serverToken);
		this.setComments(comments);
	}

	public List<Comment> getComments() {
		return results;
	}

	public void setComments(List<Comment> comments) {
		this.results = comments;
	}
}
