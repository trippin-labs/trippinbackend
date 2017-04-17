package com.trippin.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class UsersTrips {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonProperty("logged-trip")
    private Trip trip;

    public UsersTrips() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getLoggedTrip() {
        return trip;
    }

    public void setLoggedTrip(Trip loggedTrip) {
        this.trip = loggedTrip;
    }
}
