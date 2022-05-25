package com.company.service;

import com.company.container.ComponentContainer;
import com.company.controller.AdminController;
import com.company.enums.CardStatus;
import com.company.modul.Card;
import com.company.modul.Profil;
import com.company.modul.Terminal;
import com.company.modul.Transfer;
import com.company.repository.CardRepository;
import com.company.repository.TerminalRepository;
import com.company.repository.TransferRepository;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Service
public class MainService {
    @Autowired
    private AdminController adminController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TransferRepository transferRepository;


    public void aut(String login, String password) {
        Profil admin = userRepository.auth(login, password);

        if (admin == null) {
            System.out.println("mazgi tekshirmagan deb o'ylaganmiding");
        } else {
            adminController.adminMenu();
        }
    }

    public void transferMoney(Transfer transfer) {
        Card card = cardRepository.getCardNumber(transfer.getCardNumber());
        Terminal terminal = terminalRepository.getTerminalNumber(transfer.getTerminalNumber());

        if (card == null || terminal == null) {
            System.out.println("terminal or card number  wrong! mazgi");

        }
        if (card.getStatus().equals(CardStatus.BLOCK)) {
            System.out.println("card is already blocked");
        }

        if (card.getBalans() < ComponentContainer.yolkira) {
            System.out.println("Mablag' yetarli emas");
        } else {
            transfer.setCardNumber(card.getCardNumber());
            transfer.setTerminalNumber(terminal.getTerminalNumber());
            transfer.setSum(ComponentContainer.yolkira);
            transfer.setPayTerritory(terminal.getTerritory());
            boolean executeTransfer = transferRepository.saveTransfer(transfer);
            if (executeTransfer) {
                cardRepository.changeBalans(card);
                userRepository.adminCard();
            }

        }


    }

    public void payment(String cardNumber, double sum) {
        Card card = cardRepository.getCardNumber(cardNumber);

        if (card == null) {
            System.out.println("bunday karta mavjud emas");
        } else {
            cardRepository.payment(card, sum);
        }
    }
}
