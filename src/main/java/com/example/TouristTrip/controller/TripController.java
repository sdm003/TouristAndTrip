package com.example.TouristTrip.controller;

import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.services.OrderService;
import com.example.TouristTrip.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/trip")
public class TripController {
    @Autowired
    TripService tripService;
    @Autowired
    OrderService orderService;

    @GetMapping("/getAll")
    public List<Trip> getAll() {
        return tripService.getAllCities();
    }

    @PostMapping("/agreement/make")
    public Message makeAgreementToOrder(@RequestHeader Long orderId, @RequestBody Long tripId) {
        return tripService.makeAgreement(orderId, tripId);
    }

    @PutMapping("/agreement/accept")
    public Message acceptAgreement(@RequestHeader Long agreementId) {
        return tripService.acceptAgreement(agreementId);
    }

    @PostMapping("/create")
    public Message create(Principal principal, @RequestBody Trip trip) {
        System.out.println(trip.getDateOfDisactivate());
        return tripService.addTrip(trip, principal);
    }

    @GetMapping("/{id}")
    public List<Trip> getAllByCities(@PathVariable Long id) {
        return tripService.getTripByCities(id);
    }

    @DeleteMapping("/delete/{id}")
    public Message deleteTrip(@PathVariable Long id) {
        return tripService.deleteTrip(id);
    }

    @GetMapping("/orders/{id}")
    public List<Trip> getTripsByCities(@PathVariable Long id) {
        return tripService.getTripByCities(id);
    }
}
