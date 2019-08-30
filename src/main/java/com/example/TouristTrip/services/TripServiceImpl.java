package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Agreement;
import com.example.TouristTrip.entity.Orders;
import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.repository.AgreementRepository;
import com.example.TouristTrip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import java.security.Principal;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public Message addTrip(Trip trip, Principal principal) {
        Users users = userService.getUserByLogin(principal.getName());
        trip.setDelivery(users);
        tripRepository.save(trip);
        return new Message("Trip succesfully added", trip);
    }

    @Override
    @Transactional
    public Message acceptAgreement(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId).get();
        agreement.setStatusDelivery("ready");
        return new Message("Agreement has been updated", agreement);
    }

    @Override
    @Transactional
    public Message makeAgreement(Long tripId, Long orderId) {
        Trip trip = tripRepository.findById(tripId).get();
        Agreement agreement = new Agreement();
        agreement.setTrip(trip);
        Orders orders = orderService.getOrderBYId(orderId);
        agreement.setOrders(orders);
        agreement.setStatusOrder("waiting");
        agreement.setStatusDelivery("ready");
        agreementRepository.save(agreement);
        return new Message("Agreement successfully send", agreement);
    }

    @Override
    @Transactional(readOnly = true)
    public Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId).get();
    }

    @Override
    @Transactional
    public Message deleteTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).get();
        tripRepository.delete(trip);
        return new Message("Trip has been deleted", trip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agreement> getAgreementsByDelivery(Principal principal) {
        String login = principal.getName();
        agreementRepository.getAgreementsByDelivery(login);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trip> getTripByCities(Long id) {
        Orders order = orderService.getOrderBYId(id);
        return tripRepository.getItemsByCities(order.getStartPoint(), order.getEndPoint());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Trip> getAllCities() {
        return tripRepository.findAll();
    }
}
