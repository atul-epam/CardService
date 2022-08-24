package com.service.card.repository;

import com.service.card.entity.Card;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.service.card.CardServiceTestConfiguration.CARD;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CardServiceRepositoryTest {

    @Autowired
    CardServiceRepository cardServiceRepository;

    static Card savedCard;

    @Test
    void testAddNewCard() {
        savedCard = cardServiceRepository.save(CARD);
        assertNotNull(savedCard);
    }

    @Test
    void testFindByCardId() {
        Optional<Card> newCard = cardServiceRepository.findById(savedCard.getCardId());
        assertNotNull(newCard);
    }

    @Test
    void testFindAllCards() {
        List<Card> newCard = cardServiceRepository.findAll();
        assertTrue(newCard.size() > 0);
    }

}
