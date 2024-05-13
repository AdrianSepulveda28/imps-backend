package com.imps.IMPS.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HomeDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	private String announcements;
	
	private String guidelines;
	
	private String process;
	
	private String locations;
	
	private String updates;
	
	public HomeDetails() {}
	
	public HomeDetails(String ann, String guide, String pro, String loc, String upd) {
		this.setAnnouncements(ann);
		this.setGuidelines(guide);
		this.setLocations(loc);
		this.setProcess(pro);
		this.setUpdates(upd);
	}

	public String getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(String announcements) {
		this.announcements = announcements;
	}

	public String getGuidelines() {
		return guidelines;
	}

	public void setGuidelines(String guidelines) {
		this.guidelines = guidelines;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getUpdates() {
		return updates;
	}

	public void setUpdates(String updates) {
		this.updates = updates;
	}


}
