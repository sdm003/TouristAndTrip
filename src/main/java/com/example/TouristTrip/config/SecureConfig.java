package com.example.TouristTrip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/api/registration").permitAll()
                .and().authorizeRequests().antMatchers("/api/all").permitAll().antMatchers("/trip/getAll").permitAll()
                .antMatchers("/orders/getAll").permitAll().and().authorizeRequests().antMatchers("/api/all").permitAll().and()
                .authorizeRequests()
                .anyRequest().authenticated().and().logout().and().formLogin();}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws  Exception{
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login, password, is_enabled from users where login=?").
                authoritiesByUsernameQuery("select login,is_enabled from users where login=?");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}
