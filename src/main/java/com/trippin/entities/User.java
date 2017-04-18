package com.trippin.entities;

import com.trippin.utilities.PasswordStorage;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table (name = "users")
public class User implements HasId {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String password;


    public User() {
    }

    public User(String email, String username, String password) throws PasswordStorage.CannotPerformOperationException {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

