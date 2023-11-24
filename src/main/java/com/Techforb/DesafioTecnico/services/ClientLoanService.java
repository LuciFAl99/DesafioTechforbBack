package com.Techforb.DesafioTecnico.services;

import com.Techforb.DesafioTecnico.DTOs.ClientLoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientLoanService {
    public ResponseEntity<List<ClientLoanDTO>> getClientLoan(Authentication authentication);
}
