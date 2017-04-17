package com.trippin.controllers;


import com.trippin.entities.UserProfile;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.RootSerializer;
import com.trippin.serializers.UserProfileSerializer;

import com.trippin.services.UserProfileRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    UserProfileRepository userProfiles;

    public UserProfileController() {
        this.rootSerializer = new RootSerializer();
        this.userProfileSerializer = new UserProfileSerializer();
    }

    RootSerializer rootSerializer;
    UserProfileSerializer userProfileSerializer;

    //todo: add profile photo
    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (userProfiles.count() == 0) {

            UserProfile userProfile = new UserProfile();

            userProfile.setUsername("sampleuser123");
            userProfile.setHometown("hometown");
            userProfile.setHomestate("homestate");
            userProfile.setCountry("country");
            userProfile.setBio("bio bio bio");

            userProfiles.save(userProfile);
        }
    }

    //create Profile
    @RequestMapping(path = "/userProfiles", method = RequestMethod.POST)
    public HashMap<String, Object> createProfile(@RequestBody RootParser<UserProfile> parser, HttpServletResponse response) throws Exception {
        UserProfile userProfile1 = parser.getData().getEntity();
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfiles.findFirstByUsername(u.getName());
        userProfiles.save(userProfile);
        return rootSerializer.serializeOne(
                "/userProfiles" + userProfile1.getId(),
                userProfile,
                userProfileSerializer);
    }

    //get Profile
    @RequestMapping(path = "/userProfiles", method = RequestMethod.GET)
    public HashMap<String, Object> currentUser() {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfiles.findFirstByUsername(u.getName());
        return rootSerializer.serializeOne(
                "/userProfiles/" + userProfile.getId(),
                userProfile,
                userProfileSerializer);
    }
}
