package com.Techforb.DesafioTecnico.Services.Implement;

import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import com.Techforb.DesafioTecnico.Repositories.LoanRepository;
import com.Techforb.DesafioTecnico.Services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImImplement implements LoanService {
    @Autowired
    LoanRepository loanRepository;
    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }
}
