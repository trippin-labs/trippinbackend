package com.trippin.services;

import com.trippin.entities.Trip;

import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, String> {

    Trip findFirstById(String id);
}
