package com.trippin.serializers;

import java.util.HashMap;
import java.util.Map;

import com.trippin.entities.HasId;
import com.trippin.entities.User;

public class UserSerializer extends JsonDataSerializer {

    public String getType() {
        return "users";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) entity;

        result.put("email", user.getEmail());
        result.put("username", user.getUsername());

        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();

    }
}