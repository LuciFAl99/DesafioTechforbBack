package com.Techforb.DesafioTecnico.repositories;

import com.Techforb.DesafioTecnico.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
