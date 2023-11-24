package com.Techforb.DesafioTecnico.repositories;

import com.Techforb.DesafioTecnico.models.Client;
import com.Techforb.DesafioTecnico.models.ClientLoan;
import com.Techforb.DesafioTecnico.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository <ClientLoan, Long> {
    ClientLoan findByLoanAndClient(Loan loan, Client client);
}
