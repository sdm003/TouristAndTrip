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
@RequestMapping("orders")
public class OrderController {
@Autowired
OrderService orderService;
@Autowired
    TripService tripService;

    @GetMapping
    @RequestMapping("/getAll")
    public List<Orders> getAll(){
        return orderService.getAllOrders();
    }

    @PostMapping
    @RequestMapping("/create")
    public Message create(@RequestBody Orders orders,Principal principal){
        return orderService.addOrder(orders,principal);
    }
    @PostMapping
    @RequestMapping("/agreement/make")
    public Message makeAgreementToOrder(@RequestBody Long orderId,@RequestBody Long tripId){
        return orderService.makeAgreement(orderId,tripId);
    }
    @PutMapping
    @RequestMapping("/agreement/accept")
    public Message acceptAgreement(@RequestBody Long agreementId){
        return orderService.acceptAgreement(agreementId);
    }

    @GetMapping
    @RequestMapping("{id}")
    public List<Orders> getAllByCities(@PathVariable Long id){
        return orderService.getOrdersByCities(id);
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public Message deleteTrip(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }


    @GetMapping
    @RequestMapping("/trips/{id}")
    public List<Trip> getTripsByCities(@PathVariable Long id){
return tripService.getTripByCities(id);
    }

}
