package com.trippin.services;


import com.trippin.entities.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}
