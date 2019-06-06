package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
public class TripServiceImpl implements TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    UserService userService;
    @Override
    public Message addTrip(Trip trip, Principal principal) {
        Users users= userService.getUserByLogin(principal.getName());
        trip.setDelivery(users);
        tripRepository.save(trip);
        return new Message("Trip succesfully added",trip);
    }


    @Override
    public Message deleteTrip(Long tripId) {
        Trip trip= tripRepository.findById(tripId).get();
        tripRepository.delete(trip);
        return new Message("Trip has been deleted",trip);
    }

    @Override
    public List<Trip> getTripByCities(Long id) {
        Trip trip= tripRepository.findById(id).get();
        return tripRepository.getItemsByCities(trip.getStartPoint(),trip.getEndPoint());
    }

    @Override
    public List<Trip> getAllCities() {
        return tripRepository.findAll();
    }
}
