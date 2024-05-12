package com.imps.IMPS.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imps.IMPS.models.ServerResponse;
import com.imps.IMPS.models.User;
import com.imps.IMPS.models.UserResponse;
import com.imps.IMPS.repositories.UserRepository;
import com.imps.IMPS.EmailService;

@CrossOrigin
@RestController
@RequestMapping(path = "/services")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private EmailService emailService;
    
    public UserController(EmailService emailService) {
    	this.emailService = emailService;
    }

    @PostMapping(path = "/NewUserRegistration")
    public @ResponseBody UserResponse addNewUser(@RequestParam String firstName, @RequestParam String lastName
            , @RequestParam String password, @RequestParam String email) {
    	
    	try {
    		List<User> Created = new ArrayList<>();
    		String month;
    		String userNumber;
    		
    		// Generating userID logic.
    		if(LocalDate.now().getMonthValue() < 10) {
    			month = "0" + Integer.toString(LocalDate.now().getMonthValue());
    		}else {
    			month = Integer.toString(LocalDate.now().getMonthValue());
    		}
    		
    		if(userRepository.getAll().size() < 100) {
    			if (userRepository.getAll().size() < 10) {
    				userNumber = "00" + Integer.toString(userRepository.getAll().size() + 1);
    			}else {
    				userNumber = "0" + Integer.toString(userRepository.getAll().size() + 1);
    			}
    		}else {
    			userNumber = Integer.toString(userRepository.getAll().size() + 1);
    		}
    		
    		// Final userID String.
    		String userID = Integer.toString(LocalDate.now().getYear()) + month + userNumber; 
    		
    		User IMPSUser = new User();
            IMPSUser.setFirstName(firstName);
            IMPSUser.setLastName(lastName);
            IMPSUser.setEmail(email);
            IMPSUser.setPassword(password);
            IMPSUser.setToken("b457sdbfjsdf");
            IMPSUser.setIsAdmin(false);
            IMPSUser.setUserID(userID);
            Created.add(IMPSUser);
            userRepository.save(IMPSUser);
            
    		UserResponse Response = new UserResponse(true, "User created successfully", null, Created);

            return Response;
    	}catch(Exception e) {
    		UserResponse Error = new UserResponse(false, "Unable to create new user.", null, null);

    		return Error;
    	}
        
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping(path = "/getid")
    public @ResponseBody User getUserID(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
    
    @GetMapping(path = "/getname")
    public @ResponseBody User getEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
    
    @GetMapping(path = "/checkAdmin")
    public @ResponseBody Boolean checkAdmin(@RequestParam String email) {
    	if(userRepository.checkAdminEmail(email) != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    @GetMapping(path = "/userLogin")
    public @ResponseBody ServerResponse checkAuth(@RequestParam String email, 
    		@RequestParam String password) {
    	ServerResponse Response = new ServerResponse();
    	if(userRepository.findByEmailAndPassword(email, password) != null) {
    		if(userRepository.checkAdmin(email, password) != null) {
    			Response.setStatus(true);
        		Response.setMessage("Admin login");
        		Response.setServerToken(null);
        		return Response;
    		}else {
    		Response.setStatus(true);
    		Response.setMessage("User login");
    		Response.setServerToken(null);
    		return Response;
    		}
    	}else {
    		Response.setStatus(false);
    		Response.setMessage("Authentication failed.");
    		Response.setServerToken(null);
    		return Response;
    	}
    }
    
    
    
    @PostMapping(path = "/ForgotPasswordStep1")
    public @ResponseBody boolean forgotPassword(@RequestParam String email) {
    	String token = "s4mpleT0k3N";
    	userRepository.setNewToken(email, token);
    	emailService.sendEmail(email, "IMPS Password Reset Token","Hello, here is your password reset token: " + token);
    	return true;
    }
    
    @GetMapping(path = "/CheckEmail")
    public @ResponseBody boolean checkToken(@RequestParam String email) {
    	if(userRepository.findByEmail(email)!=null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    @GetMapping(path = "/ForgotPasswordStep2")
    public @ResponseBody boolean checkToken(@RequestParam String email, 
    		@RequestParam String token) {
    	if(userRepository.findByEmailAndToken(email, token) != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    @PostMapping(path = "/ForgotPasswordStep3")
    public @ResponseBody boolean setNewPassword(@RequestParam String email,
    		@RequestParam String token, @RequestParam String password) {
    	userRepository.setNewPassword(password, email, token);
    	emailService.sendEmail(email, "IMPS Password Reset","Hello, your password has been successfully changed.");
    	return true;
    }

}