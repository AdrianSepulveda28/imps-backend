package com.imps.imps.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.imps.imps.Entity.UserEntity;
import com.imps.imps.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // C
    public UserEntity insertUser(UserEntity user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepo.save(user);
    }

    // Logging In
    public boolean validateUser(String username, String password) {
        UserEntity user = userRepo.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    // R
    public UserEntity findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    // R
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    // Updating Password
    public boolean updatePassword(Integer userId, String newPassword) {
        UserEntity user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        return true;
    }

    public byte[] getUserPicture(Integer userId) {
        return userRepo.findById(userId)
                .map(UserEntity::getPicture)
                .orElseThrow(() -> new IllegalStateException("User with ID " + userId + " not found"));
    }

    // Updating Picture
    public boolean updatePicture(String username, MultipartFile picture) {
        try {
            UserEntity user = userRepo.findByUsername(username);
            if (user == null) {
                throw new IllegalStateException("User not found");
            }

            byte[] pictureB = picture.getBytes();

            user.setPicture(pictureB);

            userRepo.save(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // U - TO BE UTILIZED FOR THE SETTINGS PAGE
    public UserEntity updateUser(int user_id, UserEntity newUserDetails) {
        Optional<UserEntity> userOptional = userRepo.findById(user_id);

        if (!userOptional.isPresent()) {
            throw new NoSuchElementException("User " + user_id + " not found!");
        }

        UserEntity user = userOptional.get();

        if (newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newUserDetails.getPassword());
            user.setPassword(encodedPassword);
        }

        if (newUserDetails.getUsername() != null && !newUserDetails.getUsername().isEmpty()) {
            user.setUsername(newUserDetails.getUsername());
        }

        if (newUserDetails.getFirstname() != null && !newUserDetails.getFirstname().isEmpty()) {
            user.setFirstname(newUserDetails.getFirstname());
        }

        if (newUserDetails.getLastname() != null && !newUserDetails.getLastname().isEmpty()) {
            user.setLastname(newUserDetails.getLastname());
        }

        if (newUserDetails.getEmail() != null && !newUserDetails.getEmail().isEmpty()) {
            user.setEmail(newUserDetails.getEmail());
        }

        if (newUserDetails.isDeleted() != user.isDeleted()) {
            user.setDeleted(newUserDetails.isDeleted());
        }

        return userRepo.save(user);
    }

    // D
    public String deleteUser(int user_id) {
        String msg = "";

        if (userRepo.findById(user_id) != null) {
            userRepo.deleteById(user_id);
            msg = "User " + user_id + " is deleted!";
        } else {
            msg = "User " + user_id + " not found!";
        }
        return msg;
    }
}
