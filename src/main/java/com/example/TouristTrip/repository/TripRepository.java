package com.example.TouristTrip.repository;

import com.example.TouristTrip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("select u from Trip u where u.startPoint=:startPoint and u.endPoint=:endPoint ")
    List<Trip> getItemsByCities(@Param("startPoint") String startpoint, @Param("endPoint") String endpoint);
}

