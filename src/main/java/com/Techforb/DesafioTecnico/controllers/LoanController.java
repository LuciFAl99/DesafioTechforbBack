package com.Techforb.DesafioTecnico.controllers;

import com.Techforb.DesafioTecnico.DTOs.LoanApplicationDTO;
import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import com.Techforb.DesafioTecnico.models.Loan;
import com.Techforb.DesafioTecnico.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@RestController
public class LoanController {
    @Autowired
    LoanService loanService;
    @GetMapping("/api/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }
    @PostMapping("/api/loans")
    ResponseEntity<Object> loans(Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDto){
        return loanService.loans(authentication, loanApplicationDto);
    }
    @Transactional
    @PostMapping("/api/current/loans")
    public ResponseEntity<Object> payLoan(Authentication authentication , @RequestParam long idLoan , @RequestParam String card, @RequestParam double amount) {
        return loanService.payLoan(authentication, idLoan, card, amount);
    }
    @PostMapping("/api/admin/loan")
    public ResponseEntity<Object> newLoanAdmin(@RequestBody Loan loan) {
        return loanService.newLoanAdmin(loan);
    }

}
