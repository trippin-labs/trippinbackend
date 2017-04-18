package com.trippin.controllers;

import com.trippin.entities.User;
import com.trippin.entities.UserProfile;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.RootSerializer;
import com.trippin.serializers.UserProfileSerializer;

import com.trippin.services.UserProfileRepository;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    UserProfileRepository userProfiles;

    @Autowired
    UserRepository users;

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    @Autowired
    AmazonS3Client s3;

    public UserProfileController() {
        this.rootSerializer = new RootSerializer();
        this.userProfileSerializer = new UserProfileSerializer();
    }

    RootSerializer rootSerializer;
    UserProfileSerializer userProfileSerializer;


    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (userProfiles.count() == 0) {

            UserProfile userProfile = new UserProfile();

            User user = users.findFirstByUsername("johndoe123");
            userProfile.setUser(user);
            userProfile.setHometown("hometown");
            userProfile.setHomestate("homestate");
            userProfile.setCountry("country");
            userProfile.setBio("bio bio bio");

            userProfiles.save(userProfile);
        }
    }

    //get Profile
    @RequestMapping(path = "/userProfiles", method = RequestMethod.GET)
    public HashMap<String, Object> currentUser() {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByUsername(u.getName());
        UserProfile userProfile = userProfiles.findFirstByUser(user);

        return rootSerializer.serializeOne(
                "/userProfiles/" + userProfile.getId(),
                userProfile,
                userProfileSerializer);
    }

    @RequestMapping(path = "/userProfiles/edit", method = RequestMethod.POST)
    public HashMap<String, Object> createUserProfile(@RequestParam("photo-url") MultipartFile file,
                                                     @RequestParam("hometown") String hometown,
                                                     @RequestParam("homestate") String homestate,
                                                     @RequestParam("country") String country,
                                                     @RequestParam("bio") String bio) throws Exception {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByUsername(u.getName());

        UserProfile userProfile = new UserProfile();

        userProfile.setHometown(hometown);
        userProfile.setHomestate(homestate);
        userProfile.setCountry(country);
        userProfile.setBio(bio);
        userProfile.setUser(user);

        if (file != null) {

            userProfile.setPhotoUrl("https://s3.amazonaws.com/" + bucket + "/" + file.getOriginalFilename());

            PutObjectRequest s3Req = new PutObjectRequest(bucket, file.getOriginalFilename(), file.getInputStream(),
                    new ObjectMetadata());

            s3.putObject(s3Req);

        } else {
            userProfile.setPhotoUrl("http://bit.ly/1McvBcY");
        }

        userProfiles.save(userProfile);

        return rootSerializer.serializeOne(
                "/UserProfiles-edit" +
                        userProfile.getId(), userProfile, userProfileSerializer);

    }

    //todo: create get userProfile route
    //display users profile
}

