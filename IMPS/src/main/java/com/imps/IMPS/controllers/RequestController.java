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
import org.springframework.web.multipart.MultipartFile;

import com.imps.IMPS.models.File;
import com.imps.IMPS.models.Notification;
import com.imps.IMPS.models.PrintingDetails;
import com.imps.IMPS.models.PrintingRecord;
import com.imps.IMPS.models.RequestResponse;
import com.imps.IMPS.models.ServerResponse;
import com.imps.IMPS.repositories.FileRepository;
import com.imps.IMPS.repositories.NotificationRepository;
import com.imps.IMPS.repositories.PrintingDetailsRepository;
import com.imps.IMPS.repositories.PrintingRecordsRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/requests")
public class RequestController {
	@Autowired
    private PrintingDetailsRepository printingDetailsRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
    private PrintingRecordsRepository recordRepository;
	
	@PostMapping( path = "/newRequest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RequestResponse saveRequest(@RequestParam("requestID") String requestID, @RequestParam("userID") String userID,
			@RequestParam("fileType") String fileType, @RequestParam("fileName") String fileName, @RequestParam("desc") String desc,
			@RequestParam("noOfCopies") Integer noOfCopies, @RequestParam("colored") Boolean colored, @RequestParam("stapled") Boolean stapled,
			@RequestParam("giveExam") Boolean giveExam, @RequestParam("paperSize") String paperSize, @RequestParam("bindType") String bindType,
			@RequestParam("requestDate") Date requestDate, @RequestParam("useDate") Date useDate, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("contactNumber") String number, @RequestParam("department") String department, @RequestParam("URL") String downloadURL){
	   
			try{
				PrintingDetails request = new PrintingDetails(userID, requestID, fileName, fileType, desc, noOfCopies, colored, stapled, giveExam, paperSize, bindType, requestDate, java.time.LocalDateTime.now(), useDate, name, email, number, department, downloadURL);
				Notification notification = new Notification(requestID, userID, "New Request Created!", "You have successfully created a new request and is currently pending for approval.", requestDate);
				PrintingRecord newRecord = new PrintingRecord(userID, requestID, fileType, fileName, requestDate, useDate, "Pending");
				
				
				printingDetailsRepository.save(request);
				recordRepository.save(newRecord);
				notificationRepository.save(notification);
			
			List<PrintingDetails> Created = new ArrayList<>();
			Created.add(request);
			
			RequestResponse response = new RequestResponse(true, "New request created.", null, Created);
			return response;
			}catch (Exception e) {
				RequestResponse error = new RequestResponse(false, "Unable to create new request.", null, null);
				return error;
			}
	 }
	
	@GetMapping(path = "/id")
    public @ResponseBody PrintingDetails getRequestByID(@RequestParam String id, @RequestParam String fileName) {
        return printingDetailsRepository.findByID(id, fileName);
    }
	
	
}

