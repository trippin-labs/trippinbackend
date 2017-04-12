package com.trippin.controllers.user;

import com.trippin.entities.User;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.UserSerializer;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

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
            user.setPasswordHash();
            users.save(user);
        }
    }

    //register
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public HashMap<String, Object> registerUser(@RequestBody RootParser<User> parmUser, HttpServletResponse response) throws Exception {
        User user = parmUser.getData().getEntity();
        User dbUser = users.findFirstByEmail(user.getEmail());
        if (dbUser != null ) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else
            try {
                user.setPasswordHash();
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
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public HashMap<String, Object> loginUser(@RequestBody RootParser<User> tempUser, HttpServletResponse response) throws Exception {
        User user = tempUser.getData().getEntity();
        User dbUser = users.findFirstByUsername(user.getUsername());
        HashMap<String, Object> ret;
        if (!dbUser.verifyPassword(user.getPassword())) {
            response.sendError(401, "Invalid Credentials.");
            ret = new HashMap<>();
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            ret = rootSerializer.serializeOne(
                    "/users/" + dbUser.getId(),
                    dbUser, userSerializer);
        }
        return ret;
    }

}



