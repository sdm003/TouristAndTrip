package com.example.TouristTrip.controller;

import com.example.TouristTrip.entity.Orders;
import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.services.OrderService;
import com.example.TouristTrip.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TripService tripService;

    @GetMapping("/getAll")
    public List<Orders> getAll() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create")
    public Message create(@RequestBody Orders orders, Principal principal) {
        return orderService.addOrder(orders, principal);
    }

    @PostMapping("/agreement/make")
    public Message makeAgreementToOrder(@RequestHeader Long orderId, @RequestHeader Long tripId) {
        return orderService.makeAgreement(orderId, tripId);
    }

    @PutMapping("/agreement/accept")
    public Message acceptAgreement(@RequestHeader Long agreementId) {
        return orderService.acceptAgreement(agreementId);
    }

    @GetMapping("/{id}")
    public List<Orders> getAllByCities(@PathVariable Long id) {
        return orderService.getOrdersByCities(id);
    }

    @DeleteMapping("/delete/{id}")
    public Message deleteTrip(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }


    @GetMapping("/trips/{id}")
    public List<Trip> getTripsByCities(@PathVariable Long id) {
        return tripService.getTripByCities(id);
    }
}
