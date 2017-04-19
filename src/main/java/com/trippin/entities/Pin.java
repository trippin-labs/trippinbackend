package com.trippin.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "pins")
public class Pin implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column
    Object jsonPin;

    @Column
    Photo pinPhoto;

    public Pin() {
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getJsonPin() {
        return jsonPin;
    }

    public void setJsonPin(Object jsonPin) {
        this.jsonPin = jsonPin;
    }

    public Photo getPinPhoto() {
        return pinPhoto;
    }

    public void setPinPhoto(Photo pinPhoto) {
        this.pinPhoto = pinPhoto;
    }
}
