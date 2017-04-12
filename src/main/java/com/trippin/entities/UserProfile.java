package com.trippin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userProfiles")
public class UserProfile {

    @Id
    @GeneratedValue
    String id;

    private String username;

    public String hometown;

    public String homestate;

    public String country;

    public String bio;

    public UserProfile(String id, String username, String hometown, String homestate, String country, String bio) {
        this.id = id;
        this.username = username;
        this.hometown = hometown;
        this.homestate = homestate;
        this.country = country;
        this.bio = bio;
    }

    public UserProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHomestate() {
        return homestate;
    }

    public void setHomestate(String homestate) {
        this.homestate = homestate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
