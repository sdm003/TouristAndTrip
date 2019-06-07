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
@RequestMapping("/trip")
public class TripController {
@Autowired
TripService tripService;
@Autowired
OrderService orderService;

   @GetMapping
   @RequestMapping("/getAll")
   public List<Trip> getAll(){
       return tripService.getAllCities();
   }

   @PostMapping
   @RequestMapping("/agreement/make")
   public Message makeAgreementToOrder(@RequestBody Long orderId,@RequestBody Long tripId){
     return tripService.makeAgreement(orderId,tripId);
   }
   @PutMapping
   @RequestMapping("/agreement/accept")
   public Message acceptAgreement(@RequestBody Long agreementId){
      return tripService.acceptAgreement(agreementId);
   }

   @PostMapping
   @RequestMapping("/create")
   public Message create(Principal principal, @RequestBody Trip trip){
      return tripService.addTrip(trip,principal);
   }

   @GetMapping
   @RequestMapping("{id}")
   public List<Trip> getAllByCities(@PathVariable Long id){
       return tripService.getTripByCities(id);
   }

   @DeleteMapping
   @RequestMapping("/delete/{id}")
   public Message deleteTrip(@PathVariable Long id){
      return tripService.deleteTrip(id);
   }

   @GetMapping
   @RequestMapping("/orders/{id}")
   public List<Trip> getTripsByCities(@PathVariable Long id){
      return tripService.getTripByCities(id);
   }


}