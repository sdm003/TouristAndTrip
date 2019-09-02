package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.*;
import com.example.TouristTrip.model.Message;
import com.example.TouristTrip.repository.AgreementRepository;
import com.example.TouristTrip.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    TripService tripService;

    @Override
    public Message addOrder(Orders orders, Principal principal) {
        Users users = userService.getUserByLogin(principal.getName());
        orders.setSender(users);
        Item item = orders.getItem();
        itemService.addItem(item);
        orderRepository.save(orders);
        return new Message("Trip has been created", orders);
    }


    @Override
    public Message makeAgreement(Long orderId, Long tripId) {
        Orders order = orderRepository.findById(orderId).get();
        Agreement agreement = new Agreement();
        agreement.setOrders(order);
        agreement.setStatusOrder("ready");
        Trip trip = tripService.getTripById(tripId);
        agreement.setTrip(trip);
        agreement.setStatusDelivery("waiting");
        agreementRepository.save(agreement);
        return new Message("Agreement successfully send", agreement);
    }

    @Override
    public Message makeAgreementFinished(Long agreementId,Principal principal) {
        Agreement agreement = agreementRepository.findById(agreementId).get();
        Users users= userService.getUserByLogin(principal.getName());
        if(agreement.getOrders().getSender()==users){
            agreement.setStatusOrder("FINISHED");
            agreementRepository.save(agreement);
            return new Message("FINISHED",agreement);
        }
        return new Message("This is not your agreement",null);
    }


    @Override
    public List<Agreement> getAgreementsBySender(Principal principal) {
        principal.getName();

        return agreementRepository.getAgreementsBySender(principal.getName());

    }

    @Override
    public List<Agreement> getAgreementByDelivery(Principal principal) {
        String name = principal.getName();
        return agreementRepository.getAgreementsByDelivery(name);
    }

    @Override
    public Message rateAgreement(Long agreementId, Principal principal, Mark mark) {
        Agreement agreement = agreementRepository.findById(agreementId).get();
        Users users= userService.getUserByLogin(principal.getName());
        if(agreement.getOrders().getSender()==users && agreement.getStatusOrder().equals("FINISHED")){
            agreement.setSender(mark);
            agreementRepository.save(agreement);
            return new Message("Thank you for your rate!",mark);
        }
        return new Message("This is not your agreement",null);
    }

    @Override
    public Message acceptAgreement(Long agreementId) {
        Agreement agreement = agreementRepository.findById(agreementId).get();
        agreement.setStatusOrder("ready");
        return new Message("Agreement has been updated", agreement);
    }

    @Override
    public Orders getOrderBYId(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Message deleteOrder(Long orderId) {
        Orders orders = orderRepository.findById(orderId).get();
        return new Message("orders has been deleted", orders);
    }

    @Override
    public List<Orders> getOrdersByCities(Long id) {
        Trip trip = tripService.getTripById(id);
        return orderRepository.getOrderssByCities(trip.getStartPoint(), trip.getEndPoint());
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
