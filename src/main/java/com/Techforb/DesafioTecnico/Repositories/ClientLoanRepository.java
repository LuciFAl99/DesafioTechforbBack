package com.Techforb.DesafioTecnico.Repositories;

import com.Techforb.DesafioTecnico.Models.Client;
import com.Techforb.DesafioTecnico.Models.ClientLoan;
import com.Techforb.DesafioTecnico.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepository extends JpaRepository <ClientLoan, Long> {
    ClientLoan findByLoanAndClient(Loan loan, Client client);
}
