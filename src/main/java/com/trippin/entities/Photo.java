package com.trippin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column
    String photoUrl;

    @Override
    public String getId() {
        return null;
    }
}
