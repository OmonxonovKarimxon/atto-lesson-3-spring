package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.enums.CardStatus;
import com.company.modul.Card;
import com.company.modul.Terminal;
import com.company.service.AdminService;
import com.company.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Controller
public class AdminController {
   @Autowired
    private AdminService adminService;
   @Autowired
    private UserService userService;

    public void adminMenu() {
        while (true) {
            showAdminMenu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerNum.nextInt();

            switch (action) {
                case 1:
                    cardAdd();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    changeStatus();
                    break;
                case 4:
                    cardInfo();
                    break;
                case 5:
                    AddTerminal();
                    break;
                case 6:
                    TreminalList();
                    break;
                case 7:
                    adminCard();
                    break;
                case 8:
                    TransferList();
                    break;
                case 9:
                    TransferListByCard();
                    break;
                case 10:
                    TransferListByTerminal();
                    break;
                case 0:

                    return;
                default:


            }
        }

    }

    private void TransferListByTerminal() {
        System.out.print("Enter terminal number: ");
        String terminalNumber = ComponentContainer.scannerStr.nextLine();
        adminService.transferListByTerminal(terminalNumber);
    }

    private void TransferListByCard() {
        System.out.print("Enter Card number: ");
        String cardNumber = ComponentContainer.scannerStr.nextLine();
        adminService.transferListByCard(cardNumber);
    }

    private void TransferList() {
        adminService.transferList();
    }

    private void adminCard() {
        adminService.showAdminCard();

    }

    private void TreminalList() {

        adminService.list_terminal();
    }

    private void AddTerminal() {
        System.out.print("Enter terminal number: ");
        String TerminalNumber = ComponentContainer.scannerStr.nextLine();

        System.out.print("Enter territory number: ");
        String territory = ComponentContainer.scannerStr.nextLine();

        Terminal terminal =  new Terminal();
        terminal.setTerminalNumber(TerminalNumber);
        terminal.setTerritory(territory);
        adminService.terminalRegister(terminal);


    }

    private void cardInfo() {
        System.out.print("Enter card number: ");
        String cardNumber = ComponentContainer.scannerStr.nextLine();
        userService.cardInfo(cardNumber);
    }

    private void changeStatus() {
        System.out.print("Enter card number: ");
        String cardNumber = ComponentContainer.scannerStr.nextLine();
        userService.changeStatus(cardNumber);

    }

    private void cardList() {

        adminService.list_card();
    }

    private void cardAdd() {
        System.out.print("Enter Card number: ");
        String cardNumber = ComponentContainer.scannerStr.nextLine();

        double balans = 5000.00;

        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setBalans(balans);
        card.setStatus(CardStatus.ACTIVE);
        adminService.CardRegister(card);

    }

    private void showAdminMenu() {
        System.out.println("\n");
        System.out.println("** Admin Menu **");
        System.out.println("1.Add card ");
        System.out.println("2.card list");
        System.out.println("3.change card status");
        System.out.println("4.card info");
        System.out.println("5.Add terminal");
        System.out.println("6.terminal list");
        System.out.println("7.Admin card");
        System.out.println("8.Transfer list");
        System.out.println("9.Transfer list by card");
        System.out.println("10.terminal list ty terminal");
        System.out.println("0.exit");
    }
}
