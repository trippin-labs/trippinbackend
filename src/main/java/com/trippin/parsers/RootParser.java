package com.trippin.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootParser<T> {
    com.trippin.parsers.JsonApiDataParser<T> data;

    public RootParser(
            @JsonProperty("data") com.trippin.parsers.JsonApiDataParser<T> data) {
        this.data = data;
    }

    public com.trippin.parsers.JsonApiDataParser<T> getData() {
        return this.data;
    }
}
