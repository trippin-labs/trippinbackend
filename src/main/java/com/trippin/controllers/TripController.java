package com.trippin.controllers;

import com.trippin.entities.Trip;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.TripSerializer;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.TripRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@RestController
public class TripController {

    @Autowired
    TripRepository trips;

    RootSerializer rootSerializer;
    TripSerializer tripSerializer;

    public TripController() {
        rootSerializer = new RootSerializer();
        tripSerializer = new TripSerializer();
    }

    @PostConstruct
    public void init() throws PasswordStorage.CannotPerformOperationException {
        if (trips.count() == 0) {
            Trip trip = new Trip();
            trip.setTripName("Harold's hairy adventure.");
            trip.setLocation("Bermuda");
            trips.save(trip);
        }
    }

    //log a trip
    @RequestMapping(path = "/trips", method = RequestMethod.POST)
    public HashMap<String, Object> createLoggedTrip(@RequestBody RootParser<Trip> loggedTrip) throws Exception {
        Trip trip1 = loggedTrip.getData().getEntity();
        try {
            trips.save(trip1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootSerializer.serializeOne(
                "/trips/" + trip1.getId(),
                trip1,
                tripSerializer);
    }

}
