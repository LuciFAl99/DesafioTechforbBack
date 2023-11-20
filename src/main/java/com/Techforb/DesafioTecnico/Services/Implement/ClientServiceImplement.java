package com.Techforb.DesafioTecnico.Services.Implement;

import com.Techforb.DesafioTecnico.DTOs.ClientDTO;
import com.Techforb.DesafioTecnico.Models.Client;
import com.Techforb.DesafioTecnico.Repositories.ClientRepository;
import com.Techforb.DesafioTecnico.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> register(String firstName, String lastName, String email, String password) {

        StringBuilder errors = new StringBuilder();

        if (firstName.isBlank()) {
            errors.append("Nombre es requerido\n");
        }

        if (lastName.isBlank()) {
            errors.append("Apellido es requerido\n");
        }

        if (email.isBlank()) {
            errors.append("Email es requerido\n");
        }

        if (password.isBlank()) {
            errors.append("La contraseña es requerida\n");
        } else if (password.length() < 8) {
            errors.append("La contraseña debe tener al menos 8 caracteres\n");
        }

        if (errors.length() > 0) {
            return new ResponseEntity<>(errors.toString(), HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) != null) {

            return new ResponseEntity<>("El email esta en uso", HttpStatus.FORBIDDEN);

        }

        Client newClient = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientRepository.save(newClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
