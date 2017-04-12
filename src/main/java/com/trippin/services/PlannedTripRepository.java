package com.trippin.services;

import com.trippin.entities.PlannedTrip;
import org.springframework.data.repository.CrudRepository;


public interface PlannedTripRepository extends CrudRepository<PlannedTrip, Integer> {
}
