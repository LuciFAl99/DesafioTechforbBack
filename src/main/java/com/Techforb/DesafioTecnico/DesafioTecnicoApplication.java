package com.Techforb.DesafioTecnico;


import com.Techforb.DesafioTecnico.Models.Client;
import com.Techforb.DesafioTecnico.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioTecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository) {
        return (args) -> {

        };
    }


}
