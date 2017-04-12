package com.trippin.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "loggedtrips")
public class LoggedTrip implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false)
    String cover;

    public LoggedTrip() {
    }

    public LoggedTrip(String cover) {
        this.cover = cover;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


//
//    @Column
//    String location;
//
//    @Column
//    User user;
//
//    @Column
//    String coordinates;
//
//    @Column
//    ArrayList<Photo> photos;

}
