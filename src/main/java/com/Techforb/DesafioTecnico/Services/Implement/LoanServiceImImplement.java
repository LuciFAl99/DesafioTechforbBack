package com.Techforb.DesafioTecnico.Services.Implement;

import com.Techforb.DesafioTecnico.DTOs.LoanApplicationDTO;
import com.Techforb.DesafioTecnico.DTOs.LoanDTO;
import com.Techforb.DesafioTecnico.Enums.TransactionState;
import com.Techforb.DesafioTecnico.Enums.TransactionType;
import com.Techforb.DesafioTecnico.Models.*;
import com.Techforb.DesafioTecnico.Repositories.*;
import com.Techforb.DesafioTecnico.Services.LoanService;
import com.Techforb.DesafioTecnico.Utils.LoanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImImplement implements LoanService {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> loans(Authentication authentication, LoanApplicationDTO loanApplicationDto) {
        Loan loan = this.loanRepository.findById(loanApplicationDto.getLoanId());
        Card card = this.cardRepository.findByNumber(loanApplicationDto.getDestinationCardNumber());
        Client client = this.clientRepository.findByEmail(authentication.getName());


        //Verificar que los campos no esten vacíos o no sean inválidos
        StringBuilder errorMessage = new StringBuilder();
        if (loanApplicationDto.getDestinationCardNumber().isBlank()) {
            errorMessage.append("El número de cuenta destino es requerido\n");
        }
        if (loanApplicationDto.getAmount() <= 0) {
            errorMessage.append("El monto es requerido y debe ser un número válido\n");
        }
        if (loanApplicationDto.getPayments() <= 0) {
            errorMessage.append("Cuotas es requerido y debe ser un número válido\n");
        }
        if (loanApplicationDto.getLoanId() == 0) {
            errorMessage.append("El tipo de préstamo es requerido\n");
        }


        if (errorMessage.length() > 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage.toString());
        }

        //Verificar que el préstamo exista
        if (loan == null) {
            return new ResponseEntity<>("No existe el Préstamo", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cuenta exista
        if (card == null) {
            return new ResponseEntity<>("La cuenta destino no existe", HttpStatus.FORBIDDEN);
        }

        //Verificar que el cliente exista
        if (client == null) {
            return new ResponseEntity<>("El cliente autenticado no existe", HttpStatus.FORBIDDEN);
        }
        //Verificar que el monto solicitado no exceda el monto máximo del préstamo
        if(loanApplicationDto.getAmount() > loan.getMaxAmount()){
            return new ResponseEntity<>("El monto solicitado supera el permitido", HttpStatus.FORBIDDEN);
        }

        //Verificar que la cantidad de cuotas se encuentre entre las disponibles del préstamo
        if (!loan.getPayments().contains(loanApplicationDto.getPayments())){
            return new ResponseEntity<>("Cantidad de cuotas incorrectas", HttpStatus.FORBIDDEN);
        }
        //Verificar que la cuenta de destino exista
        if(loanApplicationDto.getDestinationCardNumber() == null){
            return new ResponseEntity<>("La cuenta destino no existe", HttpStatus.FORBIDDEN);
        }
        // Verificar que el cliente no tenga ya un préstamo del mismo tipo
        ClientLoan existingLoan = this.clientLoanRepository.findByLoanAndClient(loan, client);
        if (existingLoan != null) {
            return new ResponseEntity<>("El cliente ya tiene un préstamo del mismo tipo", HttpStatus.FORBIDDEN);
        }


        //Verificar que la cuenta de destino pertenezca al cliente autenticado
        if(!card.getClient().equals(client)){
            return new ResponseEntity<>("La cuenta destino no pertenece al cliente autenticado", HttpStatus.FORBIDDEN);
        }

        ClientLoan clientLoan = new ClientLoan(loanApplicationDto.getAmount(),loanApplicationDto.getAmount()*loan.getInterest(),loanApplicationDto.getPayments(), loanApplicationDto.getPayments());
        clientLoan.setClient(client);
        clientLoan.setLoan(loan);
        clientLoan.setDate(LocalDateTime.now());
        clientLoanRepository.save(clientLoan);

        //Creo la transaccion y la guardo
        Double initialBalanceclientAcc = card.getBalance() + loanApplicationDto.getAmount();
        Transaction creditTransaction = new Transaction(TransactionType.CREDITO, TransactionState.CONFIRMED, loanApplicationDto.getAmount(),loanApplicationDto.getLoanId()+"crédito aprobado", LocalDateTime.now(),initialBalanceclientAcc);
        transactionRepository.save(creditTransaction);

        //Le asigno la transaccion a la cuenta de destino, le agrego el balance y la guardo
        card.addTransaction(creditTransaction);
        card.setBalance(card.getBalance()+creditTransaction.getAmount());
        cardRepository.save(card);

        return  new ResponseEntity<>("Create", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> payLoan(Authentication authentication, long idLoan, String card, double amount) {
        Client client = clientRepository.findByEmail(authentication.getName());
        Optional<ClientLoan> clientLoan = clientLoanRepository.findById(idLoan);
        Card cardAuthenticated = cardRepository.findByNumber(card);
        String description = "Pago de préstamo " + clientLoan.get().getLoan().getName();

        if( clientLoan == null ){
            return new ResponseEntity<>("Este préstamo no existe", HttpStatus.FORBIDDEN);
        } else if( client == null){
            return new ResponseEntity<>("No estás registrado", HttpStatus.FORBIDDEN);}
        else if( clientLoan.get().getPayments() == 0 ){
            return new ResponseEntity<>("Este préstamo ya fue pagado", HttpStatus.FORBIDDEN);
        }
        if (card.isBlank()) {
            return new ResponseEntity<>("Por favor ingresa una cuenta. ", HttpStatus.FORBIDDEN);
        } else {
            if (cardAuthenticated == null) {
                return new ResponseEntity<>("Esta cuenta no existe. ", HttpStatus.FORBIDDEN);
            } else if (!client.getCards().contains(cardAuthenticated)) {
                return new ResponseEntity<>("Esta cuenta no te pertenece. ", HttpStatus.FORBIDDEN);
            }
        }
        if (!clientLoan.get().getClient().equals(client)) {
            return new ResponseEntity<>("No solicitaste este préstamo", HttpStatus.FORBIDDEN);
        }

        int payments = LoanUtils.getPayments(clientLoan);
        int roundedAmount = LoanUtils.getRoundedAmount(amount);
        if (roundedAmount != payments) {
            return new ResponseEntity<>("Monto incorrecto de la cuota, debes pagar "+ payments, HttpStatus.FORBIDDEN);
        }

        if ( amount < 1 ){
            return new ResponseEntity<>("Ingresa un valor mayor a 0", HttpStatus.FORBIDDEN);
        }  else if ( cardAuthenticated.getBalance() < amount ){
            return new ResponseEntity<>("Saldo insuficiente en tu cuenta " + cardAuthenticated.getNumber(), HttpStatus.FORBIDDEN);}

        Double initialBalanceaccountAuth = cardAuthenticated.getBalance() - amount;
        cardAuthenticated.setBalance( cardAuthenticated.getBalance() - amount );
        clientLoan.get().setFinalAmount( clientLoan.get().getFinalAmount() - amount);

        Transaction debitLoan = new Transaction(TransactionType.DEBITO, TransactionState.CONFIRMED, amount, description , LocalDateTime.now(), initialBalanceaccountAuth);
        transactionRepository.save(debitLoan);
        cardAuthenticated.addTransaction(debitLoan);

        cardRepository.save(cardAuthenticated);

        if ( amount < clientLoan.get().getFinalAmount()){
            clientLoan.get().setPayments(clientLoan.get().getPayments() - 1 ); //para actualizar la cantidad de cuotas
        } else {
            clientLoan.get().setPayments(0);
        }
        return new ResponseEntity<>("Pago efectuado correctamente", HttpStatus.OK);
    }


}
