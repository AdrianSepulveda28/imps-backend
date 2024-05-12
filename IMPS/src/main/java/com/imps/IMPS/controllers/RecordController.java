package com.imps.IMPS.controllers;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imps.IMPS.EmailService;
import com.imps.IMPS.models.PrintingRecord;
import com.imps.IMPS.models.RecordResponse;
import com.imps.IMPS.repositories.PrintingRecordsRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/records")
public class RecordController {
	
	Date date = Date.valueOf(LocalDate.now());
	Date thisWeek = Date.valueOf(LocalDate.now().minusDays(7));
	Date past2Weeks = Date.valueOf(LocalDate.now().minusDays(14));
	Date past3Weeks = Date.valueOf(LocalDate.now().minusDays(21));
	Date pastMonth = Date.valueOf(LocalDate.now().minusDays(30));
	Date past2Months = Date.valueOf(LocalDate.now().plusDays(60));
	
	@Autowired
    private PrintingRecordsRepository recordRepository;
    private EmailService emailService;
    
    public RecordController(EmailService emailService) {
    	this.emailService = emailService;
    }
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<PrintingRecord> getAllRecords() {
        return recordRepository.findCurrent();
    }
    
    @GetMapping(path = "/id")
    public @ResponseBody Iterable<PrintingRecord> getRecordsByID(@RequestParam String id) {
        return recordRepository.findByID(id);
    }
    
    @GetMapping(path = "/requestid")
    public @ResponseBody PrintingRecord getRecordByRequestID(@RequestParam String id) {
        return recordRepository.findByRequestID(id);
    }
    
    @GetMapping(path = "/pending")
    public @ResponseBody Iterable<PrintingRecord> getPending() {
        return recordRepository.findPending();
    }
    
    @GetMapping(path = "/getModules")
    public @ResponseBody Integer getModules(String dates) {
    	return recordRepository.getModules(dates).size();
    }
    
    @GetMapping(path = "/getOfficeForms")
    public @ResponseBody Integer getOfficeForms(String dates) {
    	return recordRepository.getOfficeForms(dates).size();
    }
    
    @GetMapping(path = "/getManuals")
    public @ResponseBody Integer getManuals(String dates) {
    	return recordRepository.getManuals(dates).size();
    }
    
    @GetMapping(path = "/getExams")
    public @ResponseBody Integer getExams(String dates) {
    	return recordRepository.getExams(dates).size();
    }
    
    @GetMapping(path = "/generateid")
    public @ResponseBody Integer generateID(@RequestParam String fileType) {
        return recordRepository.findByFileType(fileType).size();
    }
    
    @PostMapping( path = "/newRecord", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RecordResponse saveRequest(@RequestParam("requestID") String requestID, @RequestParam("userID") String userID,
			@RequestParam("fileType") String fileType, @RequestParam("fileName") String fileName,
			@RequestParam("requestDate") Date requestDate, @RequestParam("useDate") Date useDate){
	   
			try{
				PrintingRecord newRecord = new PrintingRecord(userID, requestID, fileType, fileName, requestDate, useDate, "Pending");
				
			recordRepository.save(newRecord);
			
			List<PrintingRecord> Created = new ArrayList<>();
			Created.add(newRecord);
			
			RecordResponse response = new RecordResponse(true, "New record created.", null, Created);
			return response;
			}catch (Exception e) {
				RecordResponse error = new RecordResponse(false, "Unable to create new record.", null, null);
				return error;
			}
	 }
    
    @PostMapping(path = "/changeStatus")
    public @ResponseBody boolean setNewPassword(@RequestParam String requestID,
    		@RequestParam String status, @RequestParam String email) {
    	
    	recordRepository.setNewStatus(requestID, status);
    	
    	if (status == "Rejected") {
    		emailService.sendEmail(email, "Request #" + requestID + "Status Update","Hello, your printing request with ID #" + requestID + "has been REJECTED. Please check the comment under the request details regarding why.");
    	}else if(status == "In Progress") {
    		emailService.sendEmail(email, "Request #" + requestID + "Status Update","Hello, your printing request with ID #" + requestID + "is now IN PROGRESS. Please wait until the request is completed.");
    	}else if(status == "Completed") {
    		emailService.sendEmail(email, "Request #" + requestID + "Status Update","Hello, your printing request with ID #" + requestID + "is now COMPLETED. Please approach the office during operating hours to claim your documents.");
    	}
    	
    	return true;
    }
}
