package com.example.TouristTrip.repository;

import com.example.TouristTrip.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<Users,Long> {
@Query ("select u from Users u where u.email=:email")
    Users getUserByLogin(@Param("email") String login);

}
