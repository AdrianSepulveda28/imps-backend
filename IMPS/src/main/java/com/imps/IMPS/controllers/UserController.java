package com.imps.IMPS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imps.IMPS.models.User;
import com.imps.IMPS.repositories.UserRepository;


@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestParam String name
            , @RequestParam String password, @RequestParam String email) {
        // @ResponseBody means the returned User is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User IMPSUser = new User();
        IMPSUser.setName(name);
        IMPSUser.setEmail(email);
        IMPSUser.setPassword(password);
        userRepository.save(IMPSUser);
        return IMPSUser;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

}