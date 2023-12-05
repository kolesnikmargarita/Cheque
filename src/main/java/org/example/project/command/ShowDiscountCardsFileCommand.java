package org.example.project.command;

import org.example.project.classes.DiscountCard;

import java.util.ArrayList;

public class ShowDiscountCardsFileCommand implements Command {

    ChequeInformation chequeInformation;

    public ShowDiscountCardsFileCommand(ChequeInformation chequeInformation) {
        this.chequeInformation = chequeInformation;
    }

    @Override
    public void execute() {
        printDiscountCardFile(chequeInformation.showDiscountCardsFile());
    }

    public static void printDiscountCardFile(ArrayList<DiscountCard> discountCards) {
        for(DiscountCard discountCard : discountCards) {
            System.out.println("cardNumber: " + discountCard.cardNumber());
            System.out.println("discount: " + discountCard.discount());
        }
    }
}
