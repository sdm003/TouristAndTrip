package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Agreement;
import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.model.Message;
import org.springframework.data.repository.query.Param;

import java.security.Principal;
import java.util.List;

public interface TripService {
    Message addTrip(Trip trip, Principal principal);

    Message deleteTrip(Long tripId);

    Trip getTripById(Long tripId);

    List<Trip> getTripByCities(Long id);

    List<Trip> getAllCities();

    Message makeAgreement(Long tripId, Long orderId);

    Message acceptAgreement(Long agreementId);

    List<Agreement> getAgreementsByDelivery(Principal principal);
}
