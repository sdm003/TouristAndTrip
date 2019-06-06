package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.model.Message;

import java.security.Principal;
import java.util.List;

public interface TripService {
    Message addTrip(Trip trip, Principal principal);
    Message deleteTrip(Long tripId);
    List<Trip> getTripByCities(Long id);
    List<Trip>getAllCities();
}
