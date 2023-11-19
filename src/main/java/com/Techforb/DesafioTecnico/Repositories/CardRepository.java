package com.Techforb.DesafioTecnico.Repositories;

import com.Techforb.DesafioTecnico.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
}
