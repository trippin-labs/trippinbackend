package com.trippin.services;


import com.trippin.entities.UserProfilePhoto;
import org.springframework.data.repository.CrudRepository;

public interface UserProfilePhotoRepository extends CrudRepository<UserProfilePhoto, Integer> {
}
