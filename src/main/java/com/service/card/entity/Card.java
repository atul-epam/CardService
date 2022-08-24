package com.service.card.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;

    @NotNull
    @Size(min = 2, max = 30)
    private String cardHolderName;

    @NotNull
    @Size(min = 10, max = 10, message = "Size of Pan card number should be 10.")
    private String panNumber;

    @NotNull
    private LocalDate validDate;

}
