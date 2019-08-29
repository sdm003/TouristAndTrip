package com.example.TouristTrip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final Environment environment;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, Environment environment) {
        this.javaMailSender = javaMailSender;
        this.environment = environment;
    }

    @Override
    public void sendNewPasswordToEmail(String toEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Сброс пароля");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        message.setText("Ваш пароль был сброшен " + LocalDate.now() + " в " + formatter.format(LocalTime.now()) + ", новый пароль: " + newPassword);
        message.setTo(toEmail);
        message.setFrom(environment.getProperty("spring.mail.username"));
        javaMailSender.send(message);
    }
}
