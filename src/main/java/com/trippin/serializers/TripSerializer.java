package com.trippin.serializers;

import java.util.HashMap;
import java.util.Map;

import com.trippin.entities.HasId;
import com.trippin.entities.Trip;

public class TripSerializer extends JsonDataSerializer {

    public String getType() {
        return "trips";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Trip loggedTrip = (Trip) entity;

        result.put("trip-name", loggedTrip.getTripName());
        result.put("photo-url", loggedTrip.getPhotoUrl());
        result.put("photo-urls", loggedTrip.getPhotoUrls());
        result.put("location", loggedTrip.getLocation());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
