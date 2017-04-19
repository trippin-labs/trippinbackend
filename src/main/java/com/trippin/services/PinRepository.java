package com.trippin.services;


import com.trippin.entities.Pin;
import org.springframework.data.repository.CrudRepository;

public interface PinRepository extends CrudRepository<Pin, String> {

}
