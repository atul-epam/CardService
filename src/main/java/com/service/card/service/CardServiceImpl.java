package com.service.card.service;

import com.service.card.entity.Card;
import com.service.card.exception.CardNotFoundException;
import com.service.card.repository.CardServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    final CardServiceRepository cardRepository;

    public CardServiceImpl(CardServiceRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card addNewCard(Card card) {
        return cardRepository.save(card);
    }

    public Card getCardWithCardId(Long cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.orElseThrow(() -> new CardNotFoundException("Card with Id " + cardId + " not found."));
    }
}