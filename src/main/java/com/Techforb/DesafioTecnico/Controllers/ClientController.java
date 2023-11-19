package com.Techforb.DesafioTecnico.Controllers;

import com.Techforb.DesafioTecnico.DTOs.ClientDTO;
import com.Techforb.DesafioTecnico.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;
    @GetMapping("/api/clients")
    public List<ClientDTO> getClients(){
        return clientService.getClients();
    }

}
