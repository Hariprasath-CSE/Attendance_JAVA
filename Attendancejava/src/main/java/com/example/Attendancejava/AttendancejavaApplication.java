package com.example.Attendancejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.Attendancejava.entity")
@EnableJpaRepositories("com.example.Attendancejava.repository")
public class AttendancejavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendancejavaApplication.class, args);
    }

}
