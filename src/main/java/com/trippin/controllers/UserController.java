package com.trippin.controllers;

import com.trippin.entities.User;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.UserSerializer;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository users;

    public UserController() {
        this.rootSerializer = new RootSerializer();
        this.userSerializer = new UserSerializer();
    }

    RootSerializer rootSerializer;
    UserSerializer userSerializer;

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
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public HashMap<String, Object> registerUser(@RequestBody RootParser<User> parmUser, HttpServletResponse response) throws Exception {
        User user = parmUser.getData().getEntity();
        User dbUser = users.findFirstByEmail(user.getEmail());
        if (dbUser != null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else
            try {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                users.save(user);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

    //login
    @RequestMapping(path = "/users/current", method = RequestMethod.GET)
    public HashMap<String, Object> currentUser() {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByUsername(u.getName());

        return rootSerializer.serializeOne(
                "/users/" + user.getId(),
                user,
                userSerializer);
    }

    //logout
//    @RequestMapping(path = "/users/logout", method = RequestMethod.POST)
//    public HashMap<String, Object> logout(@RequestBody RootParser<User> parmUser, HttpServletResponse response) {
//
//    }


}



