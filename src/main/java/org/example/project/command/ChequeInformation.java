package org.example.project.command;

import org.example.project.classes.Cheque;
import org.example.project.classes.DiscountCard;
import org.example.project.command.fileWork.ItemFileRedactor;
import org.example.project.classes.Item;
import org.example.project.command.chequeCreation.ChequeCreation;
import org.example.project.command.chequeCreation.converters.ConverterStringArrayIntoItemsMap;
import org.example.project.command.fileWork.DiscountCardsFileRedactor;

import java.io.IOException;
import java.util.*;

public class ChequeInformation {

    private final String[] inputArray;
    private static final String itemsFilePath = "src\\main\\java\\org\\example\\project\\command\\chequeCreation\\sources\\items.dat";
    private static final String discountCardsFilePath = "src\\main\\java\\org\\example\\project\\command\\chequeCreation\\sources\\discountCards.dat";
    private static final String chequeFilePath = "src\\main\\java\\org\\example\\project\\command\\chequeCreation\\sources\\cheque.dat";
    public ChequeInformation(String[] inputArray) {
        this.inputArray = inputArray;
    }

    public String createCheque() {
        try {
            ItemFileRedactor fileItems = new ItemFileRedactor(itemsFilePath);
            ConverterStringArrayIntoItemsMap translatorIntoItemsMap = new ConverterStringArrayIntoItemsMap(fileItems.read());
            HashMap<Integer, Item> itemsMap = translatorIntoItemsMap.translate(inputArray);
            long cardNumber =  translatorIntoItemsMap.getCardNumber();
            float discount = searchCardDiscount(discountCardsFilePath, cardNumber);
            ChequeCreation chequeCreation = new ChequeCreation(chequeFilePath, discount, itemsMap);
            Cheque cheque = chequeCreation.createCheque();
            return cheque.writeInFile() + "\n" + cheque.getResultText();
        } catch(IOException e) {
            return e.getMessage();
        }
    }

    public ArrayList<Item> showItemsFile() {
        try {
            ItemFileRedactor itemsFile = new ItemFileRedactor(itemsFilePath);
            return itemsFile.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<DiscountCard> showDiscountCardsFile() {
        try {
            DiscountCardsFileRedactor discountCardsFile = new DiscountCardsFileRedactor(discountCardsFilePath);
            return discountCardsFile.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String updateFiles() {
        ItemFileRedactor itemFile = new ItemFileRedactor(itemsFilePath);
        DiscountCardsFileRedactor discountCardsFile = new DiscountCardsFileRedactor(discountCardsFilePath);
        return itemFile.write() + "\n" + discountCardsFile.write();
    }
     public static String getItemsFilePath() {
        return itemsFilePath;
    }
    public static String getDiscountCardsFilePath() {
        return discountCardsFilePath;
    }
    public static String getChequeFilePath() {
        return chequeFilePath;
    }

    private float searchCardDiscount(String discountCardsFilePath, long inputCardNumber){
        try {
            DiscountCardsFileRedactor fileDiscountCards = new DiscountCardsFileRedactor(discountCardsFilePath);
            ArrayList<DiscountCard> discountCards = fileDiscountCards.read();
            for(DiscountCard card : discountCards) {
                if(card.cardNumber() == inputCardNumber) {
                    return card.discount();
                }
            }
        } catch (IOException e) {}
        return 0;
    }
}
