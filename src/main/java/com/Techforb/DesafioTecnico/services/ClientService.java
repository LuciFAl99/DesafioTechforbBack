package com.Techforb.DesafioTecnico.services;

import com.Techforb.DesafioTecnico.DTOs.ClientDTO;
import com.Techforb.DesafioTecnico.enums.DniType;
import com.Techforb.DesafioTecnico.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    Client findByName(String name);

    List<ClientDTO> getClients();
    ClientDTO getCurrentClient(Authentication authentication);
    public ResponseEntity<Object> register(String firstName, String lastName, DniType dniType, String dni, String password);

    interface MenuService {

    }
}
