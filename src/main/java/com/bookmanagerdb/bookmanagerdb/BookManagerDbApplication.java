package com.bookmanagerdb.bookmanagerdb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ComponentScan(basePackages = {"com.bookmanagerdb"})
@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
@EnableWebSecurity
public class BookManagerDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManagerDbApplication.class, args);
    }

}
