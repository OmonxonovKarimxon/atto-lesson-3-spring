package com.company.modul;

import com.company.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Card {
    private int id;
    private String cardNumber;
    private double balans;
    private CardStatus status;

}
