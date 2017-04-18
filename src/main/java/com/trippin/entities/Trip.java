package com.trippin.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "trips")
public class Trip implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false)
    private String tripName;

    @Column(nullable = false)
    String location;

    @ManyToOne
    private User user;

    @Column
    @JsonProperty("photo-url")
    String photoUrl;


    public Trip() {
    }

    public Trip(String tripName, String location, String photoUrl) {
        this.tripName = tripName;
        this.location = location;
        this.photoUrl = photoUrl;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
