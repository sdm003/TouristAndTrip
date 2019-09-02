package com.example.TouristTrip.services;

import com.example.TouristTrip.entity.Agreement;
import com.example.TouristTrip.entity.Mark;
import com.example.TouristTrip.entity.Orders;
import com.example.TouristTrip.model.Message;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    Message addOrder(Orders orders, Principal principal);

    Message deleteOrder(Long orderId);

    List<Orders> getOrdersByCities(Long id);

    List<Orders> getAllOrders();

    Orders getOrderBYId(Long id);

    Message makeAgreement(Long orderId, Long tripId);



    Message acceptAgreement(Long agreementId);

    Message makeAgreementFinished(Long agreementId,Principal principal);

    Message rateAgreement(Long agreementId, Principal principal, Mark mark);

    List<Agreement> getAgreementsBySender(Principal principal);
    List<Agreement>getAgreementByDelivery(Principal principal);

}
