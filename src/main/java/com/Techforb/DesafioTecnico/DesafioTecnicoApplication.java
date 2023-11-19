package com.Techforb.DesafioTecnico;


import com.Techforb.DesafioTecnico.Models.Loan;
import com.Techforb.DesafioTecnico.Repositories.ClientRepository;
import com.Techforb.DesafioTecnico.Repositories.LoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class DesafioTecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(ClientRepository clientRepository, LoanRepository loanRepository) {
        return (args) -> {

        };
    }


}
