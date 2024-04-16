package com.imps.imps.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Capstone.Imps.Service.UserRegistrationService;
import com.imps.imps.Entity.RegistrationEntity;
import com.imps.imps.Entity.UserRegistrationEntity;
import com.imps.imps.Service.RegistrationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationController registrationDto) {
        UserRegistrationEntity newUser = new UserRegistrationEntity();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(registrationDto.getPassword());
        newUser.setEmail(registrationDto.getEmail());

        UserRegistrationEntity savedUser = registrationService.registerUser(newUser);

        if (savedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
        }
    }

	private Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
