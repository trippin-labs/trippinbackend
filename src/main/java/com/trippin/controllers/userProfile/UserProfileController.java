package com.trippin.controllers.userProfile;




import com.trippin.entities.UserProfile;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.RootSerializer;
import com.trippin.serializers.UserProfileSerializer;

import com.trippin.services.UserProfileRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
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
            userProfile.setHomestate("homstate");
            userProfile.setCountry("country");
            userProfile.setBio("bio bio bio");

            userProfiles.save(userProfile);
        }
    }

    //create Profile
    @RequestMapping(path = "/userProfiles", method = RequestMethod.POST)
    public HashMap<String, Object> createProfile(@RequestBody RootParser<UserProfile> userprofile, HttpServletResponse response) throws Exception {
        UserProfile dbUserProfile = userprofile.getData().getEntity();

        return rootSerializer.serializeOne(
                "/userProfiles/" + userprofile.get
                userProfiles,
                userProfileSerializer);
}
}