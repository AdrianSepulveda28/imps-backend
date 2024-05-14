package com.imps.IMPS.models;

import java.util.List;

public class RequestResponse extends ServerResponse {
	private List<PrintingDetails> results;
	
	public RequestResponse(Boolean status, String message, String serverToken, List<PrintingDetails> requests) {
		this.setStatus(status);
		this.setMessage(message);
		this.setServerToken(serverToken);
		this.setRequests(requests);
	}

	public List<PrintingDetails> getRequests() {
		return results;
	}

	public void setRequests(List<PrintingDetails> requests) {
		this.results = requests;
	}
}
