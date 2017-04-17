package com.trippin.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

//    @Transient
//    List<User> travelGroup;

    @Column(nullable = false)
    String location;

//    @Column
//    String date;
//
//    @Column
//    String details;

    //todo: how to store photo(s) of trip.

    public Trip() {
    }

    public Trip(String tripName, String location) {
        this.tripName = tripName;
        this.location = location;
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


}
