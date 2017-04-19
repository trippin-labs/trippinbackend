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
        Trip trip = (Trip) entity;

        result.put("trip-name", trip.getTripName());
        result.put("photo-url", trip.getPhotoUrl());
        result.put("photo-urls", trip.getPhotoUrls());
        result.put("location", trip.getLocation());
        result.put("lat", trip.getLatitude());
        result.put("lng", trip.getLongitude());
        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
