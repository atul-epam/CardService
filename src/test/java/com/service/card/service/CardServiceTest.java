package com.service.card.service;

import static com.service.card.CardServiceTestConfiguration.*;
import static org.junit.jupiter.api.Assertions.*;

import com.service.card.entity.Card;
import com.service.card.exception.CardNotFoundException;
import com.service.card.repository.CardServiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardServiceRepository cardRepository;

    @Test
    void testNewCardAddedSuccessfully() {
        Mockito.when(cardRepository.save(CARD))
                .thenReturn(CARD);
        Card newCard = cardService.addNewCard(CARD);
        assertEquals(newCard,
                CARD);
    }

    @Test
    void testCardDetailsRetrievedSuccessfully() {
        Mockito.when(cardRepository.findById(VALID_CARD_ID))
                .thenReturn(Optional.of(CARD));
        Card newCard = cardService.getCardWithCardId(VALID_CARD_ID);
        assertEquals(newCard,
                CARD);
    }

    @Test
    void testCardDetailsNotAvailable() {
        Mockito.when(cardRepository.findById(INVALID_CARD_ID))
                .thenThrow(new CardNotFoundException("Card with Id 2 not found."));
        assertThrows(CardNotFoundException.class,
                        ()-> cardService.getCardWithCardId(INVALID_CARD_ID));
    }

    @Test
    void testAllCardDetailsRetrievedSuccessfully() {

        Mockito.when(cardRepository.findAll())
                .thenReturn(getCardList());
        List<Card> cardList = cardService.getAllCards();
        assertEquals(cardList,
                getCardList());
    }
}
