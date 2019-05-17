package com.example.TouristTrip.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Users {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@Column(name = "fio", nullable = false)
private String fio;

@Column(name = "login", nullable = false, unique = true)
private String email;

@Column(name = "password", nullable = false)
private String password;

@Column(name = "is_enabled",columnDefinition = "int4 default 1")
private Long isActive;

    @Override
    public boolean equals(Object obj) {
         if(this.email.equals(((Users)obj).email)) return true;
         return false;
    }

    //User u1 = new User("qweqwe", "qwewqe");
    //User u2 = new User("qweqwe", "qwqwewewqe");
    //u1.equals(u2);
    //List list = userService.findAll();
    //if(list.contains(u2) return Error

    @Column(name="image")
private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private Long ratingDelivery;

private Long ratingSender;

private LocalDate dateOFRegister;
@Column(name = "dateOfBirth", nullable = false)
@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
private LocalDate  dateOfBirth;

    public Users() {
        this.isActive=1L;
    }

    public Users(String fio, String email, String password, LocalDate dateOfBirth) {
    this.fio = fio;
    this.email = email;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.dateOFRegister=LocalDate.now();
    this.isActive=1L;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Long getRatingDelivery() {
        return ratingDelivery;
    }

    public Long getRatingSender() {
        return ratingSender;
    }

    public LocalDate getDateOFRegister() {
        return dateOFRegister;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
