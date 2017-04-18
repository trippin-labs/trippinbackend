package com.trippin.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "userProfiles")
public class UserProfile implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @OneToOne
    private User user;

    @Column
    public String hometown;

    @Column
    public String homestate;

    @Column
    public String country;

    @Column
    public String bio;

    @Column
    @JsonProperty("photo-url")
    String photoUrl;


    public UserProfile(User user, String hometown, String homestate, String country, String bio, String photoUrl) {
        this.user = user;
        this.hometown = hometown;
        this.homestate = homestate;
        this.country = country;
        this.bio = bio;
        this.photoUrl = photoUrl;
    }

    public UserProfile() {
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
