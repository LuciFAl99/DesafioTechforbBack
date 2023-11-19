package com.Techforb.DesafioTecnico.Controllers;

import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import com.Techforb.DesafioTecnico.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {
    @Autowired
    LoanService loanService;
    @GetMapping("/api/loans")
    public List<LoanDTO> getLoans(){
        return loanService.getLoans();
    }
}
