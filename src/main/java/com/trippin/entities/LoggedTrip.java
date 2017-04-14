package com.trippin.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loggedtrips")
public class LoggedTrip implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false)
    private String tripName;

    @Transient
    List<User> travelGroup;

    @Column(nullable = false)
    String location;

    @Column
    String date;

    @Column
    String details;

    //todo: how to store photo(s) of trip.

    public LoggedTrip() {
    }

    public LoggedTrip(String details) {
        this.details = details;
    }

    public LoggedTrip(String tripName, List<User> travelGroup, String location, String date, String details) {
        this.tripName = tripName;
        this.travelGroup = travelGroup;
        this.location = location;
        this.date = date;
        this.details = details;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public List<User> getTravelGroup() {
        return travelGroup;
    }

    public void setTravelGroup(List<User> travelGroup) {
        this.travelGroup = travelGroup;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
    this.date = date;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
