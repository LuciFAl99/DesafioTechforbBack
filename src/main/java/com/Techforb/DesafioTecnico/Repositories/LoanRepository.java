package com.Techforb.DesafioTecnico.Repositories;

import com.Techforb.DesafioTecnico.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository <Loan, Long> {
}
