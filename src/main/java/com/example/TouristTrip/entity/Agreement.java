package com.example.TouristTrip.entity;

import javax.persistence.*;

@Entity
@Table(name = "agreements")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Orders orders;
    @ManyToOne
    @JoinColumn
    private Trip trip;
    @ManyToOne
    @JoinColumn
    private Mark sender;
    @ManyToOne
    @JoinColumn
    private Mark delivery;
    private String statusOrder;
    private String statusDelivery;

    public Agreement(Orders orders, Trip trip) {
        this.orders = orders;
        this.trip = trip;
        this.statusDelivery="pending";
        this.statusOrder="pending";
    }

    public Agreement() {
        this.statusDelivery="pending";
        this.statusOrder="pending";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Mark getSender() {
        return sender;
    }

    public void setSender(Mark sender) {
        this.sender = sender;
    }

    public Mark getDelivery() {
        return delivery;
    }

    public void setDelivery(Mark delivery) {
        this.delivery = delivery;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(String statusDelivery) {
        this.statusDelivery = statusDelivery;
    }
}
