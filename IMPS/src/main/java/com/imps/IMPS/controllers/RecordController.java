package com.imps.IMPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imps.IMPS.EmailService;
import com.imps.IMPS.models.PrintingRecord;
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
        // This returns a JSON or XML with the users
        return recordRepository.findCurrent();
    }
    
    @GetMapping(path = "/id")
    public @ResponseBody Iterable<PrintingRecord> getRecordsByID(@RequestParam String id) {
        // This returns a JSON or XML with the users
        return recordRepository.findByID(id);
    }
    
    @GetMapping(path = "/pending")
    public @ResponseBody Iterable<PrintingRecord> getPending() {
        // This returns a JSON or XML with the users
        return recordRepository.findPending();
    }
}
