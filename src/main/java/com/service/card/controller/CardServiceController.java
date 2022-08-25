package com.service.card.controller;

import com.service.card.entity.Card;
import com.service.card.exception.InvalidCardIdException;
import com.service.card.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cardservice")
public class CardServiceController {

    final CardService cardService;

    public CardServiceController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public ResponseEntity<Object> getCards() {
        List<Card> cardList = cardService.getAllCards();

        if(cardList == null || cardList.isEmpty()) {
            return new ResponseEntity<>("No card found in database.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cardList);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<Object> getCard(@PathVariable Long cardId) {
        if(cardId <= 0) {
            throw new InvalidCardIdException("Invalid card Id.");
        }
        return ResponseEntity.ok(cardService.getCardWithCardId(cardId));
    }

    @PostMapping("new-card")
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) {
        return ResponseEntity.ok(cardService.addNewCard(card));
    }

}
