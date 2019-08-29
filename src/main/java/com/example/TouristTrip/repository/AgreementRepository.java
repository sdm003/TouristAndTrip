package com.example.TouristTrip.repository;

import com.example.TouristTrip.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    @Query("select g from Agreement g \n" +
            "    join fetch g.orders t\n" +
            "    join fetch t.sender p where p.email=:email")
    List<Agreement> getAgreementsBySender(@Param("email") String startpoint);

    @Query("select g from Agreement g \n" +
            "    join fetch g.trip t\n" +
            "    join fetch t.delivery p where p.email=:email")
    List<Agreement> getAgreementsByDelivery(@Param("email") String login);
}
