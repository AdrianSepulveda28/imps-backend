package com.imps.imps.Service;

import com.imps.imps.Entity.RegistrationEntity;
import com.imps.imps.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRegistrationRepository userregistrationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRegistrationRepository registrationRepository, PasswordEncoder passwordEncoder) {
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegistrationEntity registerUser(String username, String password, String email) {

        String encodedPassword = passwordEncoder.encode(password);

        UserRegistrationEntity newUser = new UserRegistrationEntity(username, encodedPassword, email);

        return userregistrationRepository.save(newUser);
    }

}

