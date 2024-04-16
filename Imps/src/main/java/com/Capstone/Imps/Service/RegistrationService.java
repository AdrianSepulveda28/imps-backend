package com.imps.imps.Service;

import com.imps.imps.Entity.RegistrationEntity;
import com.imps.imps.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, PasswordEncoder passwordEncoder) {
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationEntity registerUser(String username, String password, String email) {

        String encodedPassword = passwordEncoder.encode(password);

        RegistrationEntity newUser = new RegistrationEntity(username, encodedPassword, email);

        return registrationRepository.save(newUser);
    }

}

