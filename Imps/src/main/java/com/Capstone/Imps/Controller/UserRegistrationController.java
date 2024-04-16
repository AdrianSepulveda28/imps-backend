package com.Capstone.Imps.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Capstone.Imps.Service.RegistrationService; // Assuming this is the correct package for RegistrationService
import com.imps.imps.Entity.RegistrationEntity;
import com.Capstone.Imps.Dto.UserRegistrationDto; // Assuming this is the correct import for UserRegistrationDto

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    private RegistrationService registrationService; // Corrected autowired field

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        RegistrationEntity newUser = new RegistrationEntity();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(registrationDto.getPassword());
        newUser.setEmail(registrationDto.getEmail());

        RegistrationEntity savedUser = registrationService.registerUser(newUser);

        if (savedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
        }
    }
}
