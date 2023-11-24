package com.Techforb.DesafioTecnico.Repositories;


import com.Techforb.DesafioTecnico.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByDni(String dni);
    Client findByName(String name);

}
