package com.example.TouristTrip.repository;

import com.example.TouristTrip.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("select u from Orders u where u.startPoint=:startPoint and u.endPoint=:endPoint")
    List<Orders> getOrderssByCities(@Param("startPoint") String startpoint, @Param("endPoint") String endpoint);
}
