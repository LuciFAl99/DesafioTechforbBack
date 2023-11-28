package com.Techforb.DesafioTecnico.services.implement;

import com.Techforb.DesafioTecnico.models.Card;
import com.Techforb.DesafioTecnico.repositories.CardRepository;
import com.Techforb.DesafioTecnico.services.CardService;
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
