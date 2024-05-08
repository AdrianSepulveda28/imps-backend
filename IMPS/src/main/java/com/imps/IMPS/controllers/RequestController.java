package com.imps.IMPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imps.IMPS.models.File;
import com.imps.IMPS.repositories.FileRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/requests")
public class RequestController {
	@Autowired
    private FileRepository fileRepository;
	
	@PostMapping( path = "/saveFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String saveFile(@RequestParam("file") MultipartFile file){
	     try {
	      String fileName = file.getOriginalFilename();
	      String contentType = file.getContentType();
	      byte[] fileContent = file.getBytes();
	      File savefile = new File(fileName, contentType, fileContent);
	      fileRepository.save(savefile);
	    
	      return "Success";
	     }
	   
	     catch(Exception e) {
	      return "Failed";
	    }
	 }
}

