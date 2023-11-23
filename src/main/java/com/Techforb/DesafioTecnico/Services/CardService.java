package com.Techforb.DesafioTecnico.Services;

import com.Techforb.DesafioTecnico.Models.Card;

public interface CardService {
    Card findByNumber (String number);
    void saveCard(Card card);
}
