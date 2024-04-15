package com.imps.imps.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imps.imps.Entity.UserEntity;
import com.imps.imps.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/print")
    public String printHello() {
        return "Hello, Mic Test!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        boolean isValidUser = userService.validateUser(loginDto.getUsername(), loginDto.getPassword());
        if (isValidUser) {
            return ResponseEntity.ok().body("User authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/insertUser")
    public ResponseEntity<?> insertUser(@RequestBody UserRegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        user.setFirstname(registrationDto.getFirstname());
        user.setLastname(registrationDto.getLastname());

        UserEntity savedUser = userService.insertUser(user);

        UserDto userDto = mapToUserDto(savedUser);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails(@RequestParam String username) {
        UserEntity user = userService.findUserByUsername(username);
        if (user != null) {
            UserDto userDto = mapToUserDto(user);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateRequestDto request) {
        boolean updateSuccessful = userService.updatePassword(
                request.getUserId(),
                request.getNewPassword());

        if (updateSuccessful) {
            return ResponseEntity.ok("Password updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect current password or user ID.");
        }
    }

    private UserDto mapToUserDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUser_id());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstname());
        userDto.setLastName(user.getLastname());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @PostMapping("/updatePicture")
    public ResponseEntity<?> updatePicture(@RequestParam("username") String username,
            @RequestParam("picture") MultipartFile picture) {

        boolean isUpdated = userService.updatePicture(username, picture);

        if (isUpdated) {
            return ResponseEntity.ok("Picture updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update picture.");
        }
    }

    @GetMapping("/{userId}/picture")
    public ResponseEntity<?> getPicture(@PathVariable("userId") Integer userId) {
        try {
            byte[] image = userService.getUserPicture(userId);
            if (image != null && image.length > 0) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(new ByteArrayResource(image));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found or empty");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving image: " + e.getMessage());
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser/{user_id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable int user_id, @RequestBody UserEntity newUserDetails) {
        UserEntity updatedUser = userService.updateUser(user_id, newUserDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{user_id}")
    public String deleteUser(@PathVariable int user_id) {
        return userService.deleteUser(user_id);
    }
}
