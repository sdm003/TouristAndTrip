package com.example.TouristTrip.repository;

import com.example.TouristTrip.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u where u.email=:email")
    Users getUserByLogin(@Param("email") String login);

    @Query("select u from Users u where u.password=:password")
    Users getUserByPassword(@Param("password") String password);

    Users findByEmail(String email);
}
