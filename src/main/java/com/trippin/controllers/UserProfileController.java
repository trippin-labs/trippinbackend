package com.trippin.controllers;


import com.trippin.entities.UserProfile;
import com.trippin.entities.UserProfilePhoto;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.RootSerializer;
import com.trippin.serializers.UserProfileSerializer;

import com.trippin.services.UserProfilePhotoRepository;
import com.trippin.services.UserProfileRepository;
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
    UserProfilePhotoRepository photos;

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
    @RequestMapping(path = "/user-profiles", method = RequestMethod.POST)
    public HashMap<String, Object> createProfile(@RequestBody RootParser<UserProfile> parser, HttpServletResponse response) throws Exception {
        UserProfile userProfile1 = parser.getData().getEntity();
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfiles.findFirstByUsername(u.getName());
        userProfiles.save(userProfile);
        return rootSerializer.serializeOne(
                "/user-profiles" + userProfile1.getId(),
                userProfile,
                userProfileSerializer);
    }

    //get Profile
    @RequestMapping(path = "/user-profiles", method = RequestMethod.GET)
    public HashMap<String, Object> currentUser() {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfiles.findFirstByUsername(u.getName());
        return rootSerializer.serializeOne(
                "/user-profiles/" + userProfile.getId(),
                userProfile,
                userProfileSerializer);
    }

    @RequestMapping(path = "/user-profiles/upload", method = RequestMethod.POST)
    public HashMap<String, Object> uploadPhoto(@RequestParam("photo") MultipartFile file,
                                               @RequestParam("hometown") String hometown, @RequestParam("homestate") String homestate,
                                               @RequestParam("country") String country,
                                               @RequestParam("bio") String bio) throws Exception {
        //creating a new PhotoPost Entity
        UserProfile userProfile = new UserProfile();

        userProfile.setHometown(hometown);
        userProfile.setHomestate(homestate);
        userProfile.setCountry(country);
        userProfile.setBio(bio);

        userProfile.setPhotoUrl("https://s3.amazonaws.com/" + bucket + "/" + file.getOriginalFilename());

        PutObjectRequest s3Req = new PutObjectRequest(bucket, file.getOriginalFilename(), file.getInputStream(),
                new ObjectMetadata());

        s3.putObject(s3Req);

        userProfiles.save(userProfile);

        return rootSerializer.serializeOne(
                "/user-profiles" +
                 userProfile.getId(), userProfile, userProfileSerializer);

    }
}
