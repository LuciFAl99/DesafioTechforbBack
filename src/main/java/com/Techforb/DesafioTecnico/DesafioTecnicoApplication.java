package com.Techforb.DesafioTecnico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DesafioTecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApplication.class, args);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData() {
        return (args) -> {

        };
    }


}
