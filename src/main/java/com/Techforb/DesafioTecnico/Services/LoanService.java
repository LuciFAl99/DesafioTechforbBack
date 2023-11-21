package com.Techforb.DesafioTecnico.Services;

import com.Techforb.DesafioTecnico.DTOs.LoanApplicationDTO;
import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import com.Techforb.DesafioTecnico.Models.Loan;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getLoans();
    ResponseEntity<Object> loans(Authentication authentication, LoanApplicationDTO loanApplicationDto);
    public ResponseEntity<Object> payLoan(Authentication authentication, long idLoan, String card, double amount);
    ResponseEntity<Object> newLoanAdmin(@RequestBody Loan loan);
}
