package com.Techforb.DesafioTecnico.Services.Implement;

import com.Techforb.DesafioTecnico.Models.Card;
import com.Techforb.DesafioTecnico.Repositories.CardRepository;
import com.Techforb.DesafioTecnico.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }


}
