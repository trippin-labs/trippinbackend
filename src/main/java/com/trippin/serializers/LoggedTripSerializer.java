package com.trippin.serializers;

import java.util.HashMap;
import java.util.Map;

import com.trippin.entities.HasId;
import com.trippin.entities.LoggedTrip;

public class LoggedTripSerializer extends JsonDataSerializer {

    public String getType() {
        return "loggedtrips";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        LoggedTrip loggedTrip = (LoggedTrip) entity;

        result.put("tripName", loggedTrip.getTripName());
        result.put("location", loggedTrip.getLocation());
        result.put("date", loggedTrip.getDate());
        result.put("details", loggedTrip.getDetails());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}