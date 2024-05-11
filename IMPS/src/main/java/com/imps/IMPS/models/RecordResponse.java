package com.imps.IMPS.models;

import java.util.List;

public class RecordResponse extends ServerResponse {
	private List<PrintingRecord> results;
	
	public RecordResponse(Boolean status, String message, String serverToken, List<PrintingRecord> records) {
		this.setStatus(status);
		this.setMessage(message);
		this.setServerToken(serverToken);
		this.setRecords(records);
	}

	public List<PrintingRecord> getRecords() {
		return results;
	}

	public void setRecords(List<PrintingRecord> records) {
		this.results = records;
	}
}
