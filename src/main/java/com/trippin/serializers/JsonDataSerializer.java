package com.trippin.serializers;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.trippin.entities.HasId;


public abstract class JsonDataSerializer {
    abstract Map<String, Object> getAttributes(HasId data);

    abstract Map<String, String> getRelationshipUrls();

    abstract String getType();

    private Map<String, Object> getRelationships(String id) {
        return this.getRelationshipUrls()
                .entrySet().stream()
                .collect(Collectors.toMap((entry) -> entry.getKey(),
                        (entry) -> {
                            Map<String, Object> result = new HashMap<>();
                            Map<String, Object> links = new HashMap<>();
                            String url = entry.getValue();

                            links.put("related", url.replace("{id}", id));

                            result.put("links", links);

                            return result;
                        }));
    }

    public Map<String, Object> serialize(HasId data) {
        Map<String, Object> result = new HashMap<>();

        result.put("type", this.getType());
        result.put("id", data.getId());
        result.put("attributes", this.getAttributes(data));
        result.put("relationships", this.getRelationships(data.getId()));

        return result;
    }
}