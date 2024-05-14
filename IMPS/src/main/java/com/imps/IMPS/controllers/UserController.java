package com.imps.IMPS.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.imps.IMPS.models.HomeDetails;
import com.imps.IMPS.models.ServerResponse;
import com.imps.IMPS.models.User;
import com.imps.IMPS.models.UserResponse;
import com.imps.IMPS.repositories.HomeRepository;
import com.imps.IMPS.repositories.UserRepository;
import com.imps.IMPS.EmailService;

@CrossOrigin
@RestController
@RequestMapping(path = "/services")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private EmailService emailService;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Autowired
    private HomeRepository homeRepository;
    
    public UserController(EmailService emailService) {
    	this.emailService = emailService;
    }
  

    @PostMapping(path = "/NewUserRegistration")
    public @ResponseBody UserResponse addNewUser(@RequestParam String firstName, @RequestParam String lastName
            , @RequestParam String password, @RequestParam String email) {
    	
    	try {
    		String token = UUID.randomUUID().toString().replaceAll("-", "");
    		
    		
    		if(userRepository.findByEmail(email)!=null) {
    			UserResponse Response = new UserResponse(false, "User email already exists!", null, null);

                return Response;
    		}else {
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
                IMPSUser.setPassword(encoder.encode(password));
                IMPSUser.setToken(token);
                IMPSUser.setIsAdmin(false);
                IMPSUser.setUserID(userID);
                Created.add(IMPSUser);
                userRepository.save(IMPSUser);
                
        		UserResponse Response = new UserResponse(true, "User created successfully", null, Created);

                return Response;
    		}
    		
    	}catch(Exception e) {
    		UserResponse Error = new UserResponse(false, "Unable to create new user.", null, null);

    		return Error;
    	}
        
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.All();
    }
    
    @GetMapping(path = "/encrypt")
    public @ResponseBody String testEncrypt(@RequestParam String input) {
        return encoder.encode(input);
    }
    
    @GetMapping(path = "/decrypt")
    public @ResponseBody Boolean testDecrypt(@RequestParam String input, @RequestParam String test) {
        return encoder.matches(input, test);
    }
    
    
    
    @GetMapping(path = "/getid")
    public @ResponseBody User getUserID(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
    
    @GetMapping(path = "/exists")
    public @ResponseBody Boolean checkUser(@RequestParam String email) {
        if(userRepository.findByEmail(email)!=null) {
        	return true;
        }else {
        	return false;
        }
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
    	if(encoder.matches(password, userRepository.findByEmail(email).getPassword())==true) {
    		if(userRepository.checkAdminEmail(email) != null) {
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
    
    @GetMapping(path = "/checkAuth")
    public @ResponseBody Boolean checkAuthByPass(@RequestParam String email, 
    		@RequestParam String password) {
    	if(encoder.matches(password, userRepository.findByEmail(email).getPassword())==true) {	
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    
    @PostMapping(path = "/ForgotPasswordStep1")
    public @ResponseBody boolean forgotPassword(@RequestParam String email) {
    	String token = UUID.randomUUID().toString().replaceAll("-", "");
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
    	userRepository.setNewPassword(encoder.encode(password), email, token);
    	emailService.sendEmail(email, "IMPS Password Reset","Hello, your password has been successfully changed.");
    	return true;
    }
    
    @PostMapping(path = "/newPassword")
    public @ResponseBody boolean setPassword(@RequestParam String email, @RequestParam String password) {
    	userRepository.setNewPasswordNoToken(encoder.encode(password), email);
    	emailService.sendEmail(email, "IMPS Password Change","Hello, your password has been successfully changed.");
    	return true;
    }
    
    @PostMapping(path = "/newEmail")
    public @ResponseBody boolean setEmail(@RequestParam String newEmail, @RequestParam String email) {
    	userRepository.setNewEmail(newEmail, email);
    	emailService.sendEmail(email, "IMPS Email Change","Hello, your email, " +email+ " has been successfully changed to " + newEmail);
    	emailService.sendEmail(newEmail, "IMPS Email Change","Hello, your email, " +email+ " has been successfully changed to " + newEmail);
    	return true;
    }
    
    @PostMapping(path = "/newName")
    public @ResponseBody boolean setEmail(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
    	userRepository.setNewFirstName(firstName, email);
    	userRepository.setNewLastName(lastName, email);
    	return true;
    }
    
    @PostMapping(path = "/createHome")
    public @ResponseBody boolean setHome(@RequestParam String ann, @RequestParam String guide, @RequestParam String loc,
    		@RequestParam String pro, @RequestParam String upd) {
    		HomeDetails home = new HomeDetails(ann,guide,pro,loc,upd);
        	homeRepository.save(home);
        	return true;
    }
    
    @GetMapping(path = "/getHome")
    public @ResponseBody HomeDetails getHome() {
    	return homeRepository.findByID(homeRepository.getAll().size());
    }
    
    @GetMapping(path = "/getHomeNumber")
    public @ResponseBody Integer getHomeNumber() {
    	return homeRepository.getAll().size();
    }
    
    @PostMapping(path = "/editHome")
    public @ResponseBody HomeDetails editHome(@RequestParam String ann, @RequestParam String guide, @RequestParam String loc,
    		@RequestParam String pro, @RequestParam String upd) {
    	homeRepository.setAnnouncements(ann, 1);
    	homeRepository.setGuidelines(guide, 1);
    	homeRepository.setLocations(loc, 1);
    	homeRepository.setProcess(pro, 1);
    	homeRepository.setUpdates(upd, 1);
    	return homeRepository.findByID(1);
    }
    

}