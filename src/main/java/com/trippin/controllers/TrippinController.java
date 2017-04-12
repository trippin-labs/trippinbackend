package com.trippin.controllers;
import com.trippin.entities.*;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.*;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import jodd.json.JsonParser;

@RestController
public class TrippinController {

    RootSerializer rootSerializer = new RootSerializer();
//    LoggedTripSerializer LoggedTripSerializer = new LoggedTripSerializer();


//    return rootSerializer.serializeMany or serializeOne("/posts/1", post, postSerializer);

    @Autowired
    UserProfileRepository userProfiles;

    @Autowired
    PlannedTripRepository plannedTrips;

    @Autowired
    PhotoRepository photos;


//    @RequestMapping(path = "/register", method = RequestMethod.POST)
//    public ViewUser register(HttpServletResponse response, @RequestBody String body) throws Exception {
//        ViewUser viewUser = null;
//        JsonParser parser = new JsonParser();
//        try {
//            viewUser = parser.parse(body, ViewUser.class);
//
//        } catch (Exception e) {
//            response.sendError(409, "Error. Unable to parse.");
//        }
//
//        if (users.findFirstByEmail(viewUser.getEmail()) != null) {
//            response.sendError(409, "Error. An account is already registered to that email.");
//        } else {
//
//            if (users.findFirstByUsername(viewUser.getUsername()) != null) {
//                response.sendError(409, "Error. An account with that username already exists.");
//            }
//        }
//        return viewUser;
//    }



    @RequestMapping(path = "/setUpProfile", method = RequestMethod.POST)
    public void setUpProfile(@RequestBody String body) throws Exception {
        UserProfile userProfile;
        JsonParser parser = new JsonParser();
        userProfile = parser.parse(body, UserProfile.class);
        userProfiles.save(userProfile);
    }

    //return object always
    @RequestMapping(path = "/planATrip", method = RequestMethod.POST)
    public void planATrip(@RequestBody String body) throws Exception {
        PlannedTrip plannedTrip;
        JsonParser parser = new JsonParser();
        plannedTrip = parser.parse(body, PlannedTrip.class);
        plannedTrips.save(plannedTrip);
    }

    //add a photo

}
