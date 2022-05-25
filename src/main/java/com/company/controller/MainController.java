package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.modul.Transfer;
import com.company.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    public void start() {
        while (true) {
            show_menu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerNum.nextInt();

            switch (action) {
                case 1:
                    //login for admin
                    authorization();
                    break;
                case 2:
                    // transfer
                    transfer();
                    break;
                case 3:
                    //pul tashlash
                    payment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Mazgi to'gri sonni tanlang.");
            }
        }
    }

    private void payment() {
        System.out.println("enter card number");
        String cardNumber = ComponentContainer.scannerStr.nextLine();

        System.out.println("enter sum ");
        double sum = ComponentContainer.scannerDouble.nextDouble();
        mainService.payment(cardNumber, sum);
    }

    private void transfer() {
        System.out.println("enter card number");
        String cardNumber = ComponentContainer.scannerStr.nextLine();

        System.out.println("enter terminal number");
        String terminalNumber = ComponentContainer.scannerStr.nextLine();

        Transfer transfer = new Transfer();
        transfer.setCardNumber(cardNumber);
        transfer.setTerminalNumber(terminalNumber);
        mainService.transferMoney(transfer);


    }

    public void show_menu() {
        System.out.println("** Menu **");
        System.out.println("1. Login");
        System.out.println("2. Transfer");
        System.out.println("3. payment");
        System.out.println("0. Exit");
    }


    public void authorization() {
        System.out.print("Enter login: ");
        String login = ComponentContainer.scannerStr.nextLine();

        System.out.print("Enter Password: ");
        String password = ComponentContainer.scannerStr.nextLine();
        mainService.aut(login, password);
    }
}

