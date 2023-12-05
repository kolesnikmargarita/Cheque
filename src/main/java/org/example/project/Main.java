package org.example.project;


import org.example.project.command.*;


public class Main {

    public static void main(String[] args) {

        ChequeInformation chequeInformation = new ChequeInformation(args);

        Redactor redactor = new Redactor(
                new CreateChequeCommand(chequeInformation),
                new ShowItemsFileCommand(chequeInformation),
                new ShowDiscountCardsFileCommand(chequeInformation),
                new UpdateFilesCommand(chequeInformation)
        );

        switch(determinateOperation(args)) {
            case UPDATE_FILES -> redactor.updateFileInformation();
            case SHOW_ITEMS_FILE -> redactor.showItemsFileInformation();
            case SHOW_DISCOUNT_CARD_FILE -> redactor.showDiscountCardInformation();
            case CREATE_CHEQUE -> redactor.createChequeInformation();
            case INCORRECT_INPUT -> incorrectInputMassage();
        }
    }

    private static CommandsOptions determinateOperation(String[] inputArray) {
        if(inputArray.length == 1 && inputArray[0].equals("items.dat")) {
            return CommandsOptions.SHOW_ITEMS_FILE;
        } else if(inputArray.length == 1 && inputArray[0].equals("discountCards.dat")){
            return CommandsOptions.SHOW_DISCOUNT_CARD_FILE;
        } else if(inputArray.length >= 1 && checkItemsString(inputArray)) {
            return CommandsOptions.CREATE_CHEQUE;
        } else if(inputArray.length == 0) {
            return CommandsOptions.UPDATE_FILES;
        }
        return CommandsOptions.INCORRECT_INPUT;
    }

    private static void incorrectInputMassage() {
        System.out.println("Incorrect input. Reload program.");
    }

    private static boolean checkItemsString(String[] inputArray) {
        int hyphenIndex;
        for(String string : inputArray) {
            hyphenIndex = string.indexOf('-');
            if(hyphenIndex <= 0 || !itIsANumber(string.substring(hyphenIndex + 1)) ||
                    !(itIsANumber(string.substring(0, hyphenIndex)) || string.toLowerCase().startsWith("card-"))) {
                return false;
            }
        }
        return true;
    }

    private static boolean itIsANumber(String str) {
        for(char symbol : str.toCharArray() ) {
            if(!(symbol >= '0' && symbol <= '9')) {
                return false;
            }
        }
        return true;
    }
}