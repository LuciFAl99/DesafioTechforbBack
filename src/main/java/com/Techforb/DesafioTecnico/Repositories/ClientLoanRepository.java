package com.Techforb.DesafioTecnico.Repositories;

import com.Techforb.DesafioTecnico.Models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository <ClientLoan, Long> {
}
