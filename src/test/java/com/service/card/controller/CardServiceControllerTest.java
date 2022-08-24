package com.service.card.controller;

import com.service.card.exception.InvalidCardIdException;
import com.service.card.service.CardService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.card.CardServiceTestConfiguration.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class CardServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CardService cardService;

    @Test
    void testNewCardIsSaved() throws Exception {
        Mockito.when(cardService.addNewCard(ArgumentMatchers.any())).thenReturn(CARD);

        mockMvc.perform(post("/new-card")
                           .contentType(MediaType.APPLICATION_JSON_VALUE)
                           .content(mapToJson(CARD)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.cardHolderName", Matchers.equalTo(USER_NAME)))
                .andExpect(jsonPath("$.panNumber", Matchers.equalTo(PAN_NUMBER)))
                .andExpect(jsonPath("$.validDate", Matchers.equalTo(VALIDITY)));
    }

    @Test
    void testNewCardWithIncorrectDetails() throws Exception {
        Mockito.when(cardService.addNewCard(ArgumentMatchers.any())).thenReturn(INVALID_CARD);

        mockMvc.perform(post("/new-card")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testToGetAllCards() throws Exception {
        Mockito.when(cardService.getAllCards()).thenReturn(getCardList());

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].cardHolderName", Matchers.equalTo(USER_NAME)))
                .andExpect(jsonPath("$[0].panNumber", Matchers.equalTo(PAN_NUMBER)))
                .andExpect(jsonPath("$[0].validDate", Matchers.equalTo(VALIDITY)));
    }

    @Test
    void testToGetCardDetail() throws Exception {
        Mockito.when(cardService.getCardWithCardId(ArgumentMatchers.any())).thenReturn(CARD);

        mockMvc.perform(get("/card/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.cardHolderName", Matchers.equalTo(USER_NAME)))
                .andExpect(jsonPath("$.panNumber", Matchers.equalTo(PAN_NUMBER)))
                .andExpect(jsonPath("$.validDate", Matchers.equalTo(VALIDITY)));
    }

    @Test
    void testToGetCardDetailWithInvalidId() throws Exception {
        Mockito.when(cardService.getCardWithCardId(INVALID_CARD_ID)).thenThrow(new InvalidCardIdException("Invalid card Id."));

        mockMvc.perform(get("/card/2/"))
                .andExpect(status().isNotAcceptable());
    }
}
