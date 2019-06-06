package com.example.TouristTrip.entity;

import javax.persistence.*;

@Entity
@Table(name="mark")
public class Mark{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "review",nullable=false)
    private String Review;

    @Column(name = "num",nullable = false)
    private Long num;

    public Mark(String review, Long num) {
        Review = review;
        this.num = num;
    }

    public Mark() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}

