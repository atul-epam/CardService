package com.service.card.service;

import com.service.card.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    List<Card> getAllCards();
    Card addNewCard(Card card);
    Card getCardWithCardId(Long cardId);
}
