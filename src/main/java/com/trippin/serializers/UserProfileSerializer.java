package com.trippin.serializers;


import com.trippin.entities.HasId;
import com.trippin.entities.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class UserProfileSerializer {

    public String getType() {
        return "userProfiles";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        UserProfile userProfile = (UserProfile) entity;

        result.put("userName", userProfile.getUsername());
        result.put("hometown", userProfile.getHometown());
        result.put("homestate", userProfile.getHomestate());
        result.put("country", userProfile.getCountry());
        result.put("bio", userProfile.getBio());


        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}

