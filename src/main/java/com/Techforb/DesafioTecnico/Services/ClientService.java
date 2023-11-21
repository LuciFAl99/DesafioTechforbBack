package com.Techforb.DesafioTecnico.Services;

import com.Techforb.DesafioTecnico.DTOs.ClientDTO;
import com.Techforb.DesafioTecnico.Enums.DniType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
    ClientDTO getCurrentClient(Authentication authentication);
    public ResponseEntity<Object> register(String firstName, String lastName, DniType dniType, String dni, String password);
}
