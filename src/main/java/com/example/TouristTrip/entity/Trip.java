package com.example.TouristTrip.entity;

import com.example.TouristTrip.Enumeration.Transport;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String startPoint;
    private String endPoint;
    @ManyToOne
    @JoinColumn(name="sender")
    private Users delivery;

    @Column(name = "dateOfDisactivate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfDisactivate;

    private String description;
    @Enumerated(EnumType.STRING)
    private Transport transport;
    private String status;
    private LocalDate dateOfCreation;
    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Trip() {
        this.dateOfCreation=LocalDate.now();
        this.status="waiting";
    }

    public Trip(String startPoint, String endPoint,LocalDate dateOfDisactivate, String description, Transport transport) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.dateOfDisactivate = dateOfDisactivate;
        this.description = description;
        this.transport=transport;
        this.status="pending";
        this.dateOfCreation=LocalDate.now();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getDelivery() {
        return delivery;
    }

    public void setDelivery(Users delivery) {
        this.delivery = delivery;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfDisactivate() {
        return dateOfDisactivate;
    }

    public void setDateOfDisactivate(LocalDate dateOfDisactivate) {
        this.dateOfDisactivate = dateOfDisactivate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartPoint() {
        return startPoint; }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint; }

    public String getEndPoint() {
        return endPoint; }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint; }
}
