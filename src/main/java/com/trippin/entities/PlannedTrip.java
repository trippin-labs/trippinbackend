package com.trippin.entities;


import javax.persistence.*;

@Entity
@Table(name = "plannedTrips")
public class PlannedTrip {

    @Id
    @GeneratedValue

    int id;

    @Column
    String cover;
}
