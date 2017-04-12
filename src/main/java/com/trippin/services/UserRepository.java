package com.trippin.services;

import com.trippin.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findFirstByEmail(String email);

    User findFirstByUsername(String username);

    User findUserById(String id);
}
