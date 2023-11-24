package com.Techforb.DesafioTecnico.Repositories;

import com.Techforb.DesafioTecnico.Models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
