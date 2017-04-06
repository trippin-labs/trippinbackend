package com.trippin.controllers;

import com.trippin.entities.User;
import com.trippin.entities.ViewUser;
import com.trippin.services.PhotoRepository;
import com.trippin.services.TripRepository;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import jodd.json.meta.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import jodd.json.JsonParser;

import java.io.Closeable;

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

    //register
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public User register(HttpServletResponse response, @RequestBody String body) throws Exception {
        User viewUser = null;
        JsonParser parser = new JsonParser();
        try {
            viewUser = parser.parse(body, User.class);
            users.save(viewUser);

        } catch (Exception e) {
            response.sendError(409, "Error. Unable to parse.");
        }

        if (users.findFirstByEmail(viewUser.getEmail()) != null) {
            response.sendError(409, "Error. An account is already registered to that email.");
        }
        return viewUser;
    }

    /*
    login: get input from user and check against the database to see if user exists. If user exists validate username and password
    match. If user does not exist, throw exception (need to register). If username or password does not match
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpServletResponse response, @RequestBody String body) throws Exception {

        ViewUser viewUser = null;
        JsonParser parser = new JsonParser();

        try {
            viewUser = parser.parse(body, ViewUser.class);
            //todo what does this try block do?
        } catch (Exception e) {
            response.sendError(409, "Error. Unable to parse.");
        }

        User dbuser = users.findFirstByUsername(viewUser.getUsername());

        if (dbuser == null) {
            response.sendError(401, "Account not found. Please register.");
        } else {
            if (! dbuser.verifyPassword(viewUser.getPassword())) {
                response.sendError(401, "Invalid credentials.");
            }
        }
        return dbuser;
    }


}
