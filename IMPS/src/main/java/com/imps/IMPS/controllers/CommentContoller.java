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
import com.imps.IMPS.models.Comment;
import com.imps.IMPS.models.CommentResponse;
import com.imps.IMPS.repositories.CommentRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
public class CommentContoller {
	@Autowired
	private CommentRepository commentRepository;
    private EmailService emailService;
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    @GetMapping(path = "/id")
    public @ResponseBody Iterable<Comment> getCommentsByID(@RequestParam String id) {
        return commentRepository.findByRequestID(id);
    }
    
    @PostMapping( path = "/newComment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CommentResponse saveRequest(@RequestParam("content") String content, @RequestParam("header") String header,
			@RequestParam("requestID") String requestID, @RequestParam("sentBy") String sentBy, @RequestParam("sentDate") Date sentDate){
	   
			try{
				Comment comment = new Comment(requestID, header, content, sentBy, sentDate);
				
			commentRepository.save(comment);
			
			List<Comment> Created = new ArrayList<>();
			Created.add(comment);
			
			CommentResponse response = new CommentResponse(true, "New comment added.", null, Created);
			return response;
			}catch (Exception e) {
				CommentResponse error = new CommentResponse(false, "Unable to create new comment.", null, null);
				return error;
			}
	 }

}
