package com.trippin.services;


import com.trippin.entities.Photo;
import com.trippin.entities.Trip;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}
