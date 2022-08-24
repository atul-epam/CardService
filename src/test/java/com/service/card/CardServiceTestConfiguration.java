package com.service.card;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.card.entity.Card;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardServiceTestConfiguration {
    public static final Long VALID_CARD_ID = 1L;
    public static final Long INVALID_CARD_ID = 2L;
    public static final String USER_NAME = "Test User";
    public static final String PAN_NUMBER = "ADGPE1245G";
    public static final String VALIDITY = "2022-08-23";
    public static final Card CARD = new Card(VALID_CARD_ID,
                                            USER_NAME,
                                            PAN_NUMBER,
                                            LocalDate.parse(VALIDITY));

    public static final Card INVALID_CARD = new Card(VALID_CARD_ID,
                                                    "",
                                                    PAN_NUMBER,
                                                    LocalDate.parse(VALIDITY));
    public static List<Card> getCardList() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(CARD);
        return cardList;
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(obj);
    }
}
