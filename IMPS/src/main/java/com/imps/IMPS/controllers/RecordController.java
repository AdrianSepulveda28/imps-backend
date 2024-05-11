package com.imps.IMPS.controllers;

import java.sql.Date;
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
}
