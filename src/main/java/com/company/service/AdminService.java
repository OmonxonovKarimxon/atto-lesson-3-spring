package com.company.service;

import com.company.modul.Card;
import com.company.modul.Terminal;
import com.company.repository.CardRepository;
import com.company.repository.TerminalRepository;
import com.company.repository.TransferRepository;
import com.company.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class AdminService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;


    public void CardRegister(Card card) {
        if (!isValidForBook(card)) {
            return;
        }


        if (cardRepository.isCardExists(card.getCardNumber())) {
            System.out.println("Mazgi bu carta borku.");
        } else {
            cardRepository.save_card(card);

        }

    }

    private boolean isValidForBook(Card card) {
        if (card.getCardNumber() == null || card.getCardNumber().length() < 2) {
            System.out.println("mazgi karta nomerni to'g'ri kirit.");
            return false;
        }
        return true;
    }

    public void list_card() {
        cardRepository.cardList();
    }

    public void terminalRegister(Terminal terminal) {
        if (!isValidForTerminal(terminal)) {
            return;
        }

        if (terminalRepository.isTerminalExists(terminal.getTerminalNumber())) {
            System.out.println("Mazgi bu carta borku.");
        } else {
            terminalRepository.save_terminal(terminal);
        }
    }

    private boolean isValidForTerminal(Terminal terminal) {
        if (terminal.getTerminalNumber() == null || terminal.getTerminalNumber().length() < 2) {
            System.out.println("mazgi terminal nomerni to'g'ri kirit.");
            return false;
        }
        return true;
    }

    public void list_terminal() {
        terminalRepository.terminalList();
    }

    public void showAdminCard() {
        userRepository.showAdminCard();

    }

    public void transferList() {
        transferRepository.transferList();
    }

    public void transferListByCard(String cardNumber) {
        transferRepository.transferListByCard(cardNumber);
    }

    public void transferListByTerminal(String terminalNumber) {
        transferRepository.transferListByTerminal(terminalNumber);

    }
}
