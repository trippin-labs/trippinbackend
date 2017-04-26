package com.trippin.services;

import com.trippin.entities.Trip;

import com.trippin.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, String> {

    Trip findFirstById(String id);

    Trip findAllById(String id);

    Trip findAllByUser(User user);
}
