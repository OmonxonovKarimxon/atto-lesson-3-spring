package com.company.service;

import com.company.enums.CardStatus;
import com.company.modul.Card;
import com.company.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class UserService {
    @Autowired
    private CardRepository cardRepository;
    public void changeStatus(String cardNumber) {
        Card card = cardRepository.getCardNumber(cardNumber);
        if (card == null) {
            System.out.println("bunday carta yoq");
            return;
        }
        if (card.getStatus().equals(CardStatus.ACTIVE)) {
            card.setStatus(CardStatus.BLOCK);
        } else {
            card.setStatus(CardStatus.ACTIVE);
        }
        cardRepository.changeStatus(card.getCardNumber(), card.getStatus());
        System.out.println("Success");
    }

    public void cardInfo(String cardNumber) {
        Card card = cardRepository.getCardNumber(cardNumber);
        if (card == null) {
            System.out.println("bunday carta yoq");
            return;
        }else {

            System.out.println(card);
        }

    }


}
