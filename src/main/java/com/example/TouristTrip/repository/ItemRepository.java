package com.example.TouristTrip.repository;


import com.example.TouristTrip.entity.Item;
import com.example.TouristTrip.entity.Trip;
import com.example.TouristTrip.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
