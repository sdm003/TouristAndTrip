package com.example.TouristTrip.controller;

import com.example.TouristTrip.entity.Orders;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
@Autowired
OrderService orderService;

    @GetMapping
    @RequestMapping("/getAll")
    public List<Orders> getAll(){
        return orderService.getAllOrders();
    }

    @PostMapping
    @RequestMapping("/create")
    public Message create( @RequestBody Orders orders,Principal principal){
        return orderService.addOrder(orders,principal);
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

}
