package com.Techforb.DesafioTecnico.services;

import com.Techforb.DesafioTecnico.models.Card;

public interface CardService {
    Card findByNumber (String number);
    void saveCard(Card card);
}
