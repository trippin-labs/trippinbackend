package com.trippin.entities;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    String id;
}
