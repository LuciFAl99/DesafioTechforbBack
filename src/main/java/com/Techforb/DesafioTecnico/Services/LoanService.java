package com.Techforb.DesafioTecnico.Services;

import com.Techforb.DesafioTecnico.DTOs.LoanApplicationDTO;
import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoanService {
    List<LoanDTO> getLoans();
    ResponseEntity<Object> loans(Authentication authentication, LoanApplicationDTO loanApplicationDto);
    public ResponseEntity<Object> payLoan(Authentication authentication, long idLoan, String card, double amount);
}
