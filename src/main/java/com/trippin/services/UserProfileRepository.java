package com.trippin.services;

import com.trippin.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
}
