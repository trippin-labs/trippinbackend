package com.trippin.entities;

import com.trippin.entities.HasId;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Entity
@Table(name = "profile_photos")
public class UserProfilePhoto implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
