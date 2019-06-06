package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Orders;
import com.example.TouristTrip.entity.Users;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;

    @Override
    public Message addOrder(Orders orders, Principal principal) {
    Users users =userService.getUserByLogin(principal.getName());
    orders.setSender(users);
    orderRepository.save(orders);
    return new Message("Trip has been created", orders);
    }

    @Override
    public Message deleteOrder(Long orderId) {
        Orders orders =orderRepository.findById(orderId).get();
        return new Message("orders has been deleted", orders);
    }

    @Override
    public List<Orders> getOrdersByCities(Long id) {
    Orders orders =orderRepository.findById(id).get();
    return orderRepository.getOrderssByCities(orders.getStartPoint(), orders.getEndPoint());
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
