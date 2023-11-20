package com.Techforb.DesafioTecnico.Services;

import com.Techforb.DesafioTecnico.DTOs.ClientDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
    ResponseEntity<Object> register(String firstName, String lastName, String email, String password);
}
