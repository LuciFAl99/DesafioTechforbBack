package com.Techforb.DesafioTecnico.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;

public interface TransactionService {
    ResponseEntity<Object>  transaction(Authentication authentication, double amount, String description, String accountOriginNumber, String destinationAccountNumber) ;
}
