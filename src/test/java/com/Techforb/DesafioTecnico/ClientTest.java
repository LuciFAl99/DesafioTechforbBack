package com.Techforb.DesafioTecnico;

import com.Techforb.DesafioTecnico.Enums.DniType;
import com.Techforb.DesafioTecnico.Models.Card;
import com.Techforb.DesafioTecnico.Repositories.CardRepository;
import com.Techforb.DesafioTecnico.Repositories.ClientRepository;
import com.Techforb.DesafioTecnico.Services.ClientService;
import com.Techforb.DesafioTecnico.Services.Implement.CardServiceImplement;
import com.Techforb.DesafioTecnico.Services.Implement.ClientServiceImplement;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ClientTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private  CardRepository cardRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientServiceImplement clientService;

    @Test
    public void testRegisterWithValidInput() {
        String firstName = "John";
        String lastName = "Doe";
        DniType dniType = DniType.DNI;
        String dni = "12536897";
        String password = "123456879";
        // Mock other required fields
        Card newCard = new Card();

        Mockito.when(clientRepository.findByDni(Mockito.anyString())).thenReturn(null);

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(newCard);

        ResponseEntity<Object> responseEntity = clientService.register(firstName, lastName, dniType, dni, password);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
