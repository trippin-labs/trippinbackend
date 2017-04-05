package com.trippin.controllers;


import com.trippin.entities.User;
import com.trippin.services.PhotoRepository;
import com.trippin.services.TripRepository;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;


@RestController
public class TrippinController {
    @Autowired
    UserRepository users;

    @Autowired
    TripRepository trips;

    @Autowired
    PhotoRepository photos;


    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (users.count() == 0) {
            User user = new User();
            user.setEmail("johndoe@gmail.com");
            user.setUsername("johndoe123");
            user.setPassword("password123");
            users.save(user);
        }
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public User register(HttpServletResponse response, String email, String username, String password) throws Exception {
        User user = users.findFirstByEmail(email);
        if (user == null) {
            user = new User(email, username, password);
            users.save(user);
        } else if (user != null) {
            response.sendError(409, "Whoops. Looks like an account is already registered to this email. Please login.");
        }
        return user;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, String username, String password) throws Exception {
        User user = users.findFirstByUsername(username);
        if (user == null) {
            response.sendError(401, "Account not found. Please register.");
        } else if (! user.verifyPassword(password)) {
            response.sendError(401, "Invalid credentials.");
        }
        return user;
    }



//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public User home(String username) throws Exception {
//        User user = users.findFirstByUsername(username);
//        if (user != null) {
//
//        }
//        return user;
//    }

}
