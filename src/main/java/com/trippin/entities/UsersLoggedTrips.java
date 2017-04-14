package com.trippin.entities;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class UsersLoggedTrips {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    private User user;

    @ManyToOne
    private LoggedTrip loggedTrip;

    public UsersLoggedTrips() {
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

    public LoggedTrip getLoggedTrip() {
        return loggedTrip;
    }

    public void setLoggedTrip(LoggedTrip loggedTrip) {
        this.loggedTrip = loggedTrip;
    }
}
