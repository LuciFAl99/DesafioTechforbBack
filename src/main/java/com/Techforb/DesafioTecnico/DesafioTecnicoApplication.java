package com.Techforb.DesafioTecnico;

import com.Techforb.DesafioTecnico.Enums.DniType;
import com.Techforb.DesafioTecnico.Models.Card;
import com.Techforb.DesafioTecnico.Models.Client;
import com.Techforb.DesafioTecnico.Models.ClientLoan;
import com.Techforb.DesafioTecnico.Models.Loan;
import com.Techforb.DesafioTecnico.Repositories.CardRepository;
import com.Techforb.DesafioTecnico.Repositories.ClientLoanRepository;
import com.Techforb.DesafioTecnico.Repositories.ClientRepository;
import com.Techforb.DesafioTecnico.Repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;


@SpringBootApplication
public class DesafioTecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioTecnicoApplication.class, args);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public CommandLineRunner initData(ClientLoanRepository clientLoanRepository, LoanRepository loanRepository, ClientRepository clientRepository) {
        return (args) -> {

        };
    }


}
