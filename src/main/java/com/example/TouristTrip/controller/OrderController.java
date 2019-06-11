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
    @CrossOrigin
    @GetMapping
    @RequestMapping("/getAll")
    public List<Orders> getAll(){
        return orderService.getAllOrders();
    }
    @CrossOrigin
    @PostMapping
    @RequestMapping("/create")
    public Message create(@RequestBody Orders orders,Principal principal){
        return orderService.addOrder(orders,principal);
    }
    @CrossOrigin
    @PostMapping
    @RequestMapping("/agreement/make")
    public Message makeAgreementToOrder(@RequestHeader Long orderId,@RequestHeader Long tripId){
        return orderService.makeAgreement(orderId,tripId);
    }
    @CrossOrigin
    @PutMapping
    @RequestMapping("/agreement/accept")
    public Message acceptAgreement(@RequestHeader Long agreementId){
        return orderService.acceptAgreement(agreementId);
    }
    @CrossOrigin
    @GetMapping
    @RequestMapping("{id}")
    public List<Orders> getAllByCities(@PathVariable Long id){
        return orderService.getOrdersByCities(id);
    }
    @CrossOrigin
    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public Message deleteTrip(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/trips/{id}")
    public List<Trip> getTripsByCities(@PathVariable Long id){
return tripService.getTripByCities(id);
    }

}
