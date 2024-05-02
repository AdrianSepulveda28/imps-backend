package com.imps.IMPS.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imps.IMPS.models.ServerResponse;
import com.imps.IMPS.models.User;
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
    public @ResponseBody ServerResponse addNewUser(@RequestParam String name
            , @RequestParam String password, @RequestParam String email) {
        // @ResponseBody means the returned User is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
    	
    	try {
    		ServerResponse Response = new ServerResponse();
    		Response.setStatus(false);
    		Response.setMessage("User created successfully.");
    		Response.setServerToken(null);
    		
    		List<User> Created = new ArrayList<>();
    		
    		User IMPSUser = new User();
            IMPSUser.setName(name);
            IMPSUser.setEmail(email);
            IMPSUser.setPassword(password);
            IMPSUser.setToken("b457sdbfjsdf");
            userRepository.save(IMPSUser);
            
            Created.add(IMPSUser);
            Response.setResults(Created);
            
            return Response;
    	}catch(Exception e) {
    		ServerResponse Error = new ServerResponse();
    		Error.setStatus(false);
    		Error.setMessage("Unable to create new user.");
    		Error.setServerToken(null);
    		Error.setResults(null);
    		
    		return Error;
    	}
        
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
    
    @GetMapping(path = "/userLogin")
    public @ResponseBody boolean checkAuth(@RequestParam String username, 
    		@RequestParam String password) {
    	if(userRepository.findByUsernameAndPassword(username, password) != null) {
    		return true;
    	}else {
    		return false;
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