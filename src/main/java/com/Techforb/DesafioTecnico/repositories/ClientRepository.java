package com.Techforb.DesafioTecnico.repositories;


import com.Techforb.DesafioTecnico.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByDni(String dni);
    Client findByName(String name);

}
