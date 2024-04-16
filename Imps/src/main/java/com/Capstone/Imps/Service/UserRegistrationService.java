package com.Capstone.Imps.Service;

import javax.swing.text.PasswordView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imps.imps.Entity.UserRegistrationEntity;
import com.imps.imps.Repository.UserRegistrationRepository;

@Service
public class UserRegistrationService {

    private final UserRegistrationRepository userregistrationRepository = null;
    private UserRegistrationRepository registrationRepository;
	@Autowired
    public void RegistrationService(UserRegistrationRepository registrationRepository, PasswordView passwordEncoder) {
        this.setRegistrationRepository(registrationRepository);
    }

    public UserRegistrationEntity registerUser(String username, String password, String email) {

        UserRegistrationEntity newUser = new UserRegistrationEntity(username, encodedPassword, email);

        return userregistrationRepository.save(newUser);
    }

	public UserRegistrationRepository getRegistrationRepository() {
		return registrationRepository;
	}

	public void setRegistrationRepository(UserRegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}

	public UserRegistrationEntity registerUser(UserRegistrationEntity newUser) {
		// TODO Auto-generated method stub
		return null;
	}

}

