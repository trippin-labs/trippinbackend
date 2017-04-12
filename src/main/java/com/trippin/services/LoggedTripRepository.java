package com.trippin.services;

import com.trippin.entities.LoggedTrip;
import org.springframework.data.repository.CrudRepository;

public interface LoggedTripRepository extends CrudRepository<LoggedTrip, String> {

}
