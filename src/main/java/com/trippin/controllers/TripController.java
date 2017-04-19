package com.trippin.controllers;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.trippin.entities.Photo;
import com.trippin.entities.Trip;
import com.trippin.entities.User;
import com.trippin.parsers.RootParser;
import com.trippin.serializers.TripSerializer;
import com.trippin.serializers.RootSerializer;
import com.trippin.services.TripRepository;
import com.trippin.services.UserRepository;
import com.trippin.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class TripController {

    @Autowired
    TripRepository trips;

    @Autowired
    UserRepository users;

    @Value("${cloud.aws.s3.bucket}")
    String bucket;

    @Autowired
    AmazonS3Client s3;

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


    @RequestMapping(path = "/trips", method = RequestMethod.GET)
    public HashMap<String, Object> allTrip() {
        Iterable<Trip> result = trips.findAll();

        return rootSerializer.serializeMany("/trips", result, tripSerializer);
    }

    //todo: add request params and photo uploading like 'createUserProfile'
    //log a trip
    @RequestMapping(path = "/trips", method = RequestMethod.POST)
    public HashMap<String, Object> createTrip(@RequestParam("name") String tripName,
                                              @RequestParam("location") String location,
                                              @RequestParam("cover-photo") MultipartFile coverPhoto,
                                              @RequestParam("photos[]") MultipartFile[] otherPhotos) throws Exception {
        Authentication u = SecurityContextHolder.getContext().getAuthentication();
        User user = users.findFirstByUsername(u.getName());
        Trip trip = new Trip();

        trip.setLocation(location);
        trip.setTripName(tripName);
        trip.setUser(user);

        trip.setPhotoUrl("https://s3.amazonaws.com/" + bucket + "/" + coverPhoto.getOriginalFilename());

        PutObjectRequest s3Req = new PutObjectRequest(bucket, coverPhoto.getOriginalFilename(), coverPhoto.getInputStream(),
                new ObjectMetadata());
        s3.putObject(s3Req);

        ArrayList<String> otherPhotoUrls = new ArrayList<>();

        for (MultipartFile photo : otherPhotos) {
            otherPhotoUrls.add("https://s3.amazonaws.com/" + bucket + "/" + photo.getOriginalFilename());

            PutObjectRequest s3Req2 = new PutObjectRequest(bucket, photo.getOriginalFilename(), photo.getInputStream(),
                    new ObjectMetadata());
            s3.putObject(s3Req2);
        }

        trip.setPhotoUrls(otherPhotoUrls);


        try {
            trips.save(trip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootSerializer.serializeOne(
                "/trips/" + trip.getId(),
                trip,
                tripSerializer);
    }

    @RequestMapping(path = "/trips/delete", method = RequestMethod.POST)
    public void deleteTrip(@RequestBody RootParser<Trip> parmTrip) {

        Trip trip = parmTrip.getData().getEntity();
        trips.delete(trip);
    }

    @RequestMapping(path = "/trips/{id)/photos/{id2}", method = RequestMethod.POST)
    public void deleteTripPhoto(@RequestBody RootParser<String> photoUrl) {

        String photo1 = photoUrl.getData().getEntity();




    }
}
