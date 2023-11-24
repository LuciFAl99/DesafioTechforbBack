package com.Techforb.DesafioTecnico.repositories;


import com.Techforb.DesafioTecnico.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository <Loan, Long> {
    Loan findById (long id);
}
