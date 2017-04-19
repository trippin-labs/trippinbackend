package com.trippin.serializers;

import java.util.HashMap;
import java.util.Map;

import com.trippin.entities.HasId;
import com.trippin.entities.Pin;

public class PinSerializer extends JsonDataSerializer {

    public String getType() {
        return "pins";
    }

    public Map<String, Object> getAttributes(HasId entity) {
        Map<String, Object> result = new HashMap<>();
        Pin pin = (Pin) entity;

        result.put("json-pin", pin.getJsonPin());
//        result.put("pin-photo"), pin.getPinPhoto();



        return result;
    }

    public Map<String, String> getRelationshipUrls() {
        return new HashMap<String, String>();
    }
}
