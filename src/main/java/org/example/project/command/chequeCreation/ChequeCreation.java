package org.example.project.command.chequeCreation;


import org.example.project.classes.Cheque;
import org.example.project.classes.builders.ChequeBuilder;
import org.example.project.command.chequeCreation.converters.ConverterItemsMapIntoString;
import org.example.project.classes.Item;

import java.time.*;
import java.util.*;

public class ChequeCreation {

    private final String chequeFilePath;
    private final float cardDiscount;
    private final HashMap<Integer, Item> itemsMap;
    private final String dateToday;
    private final String timeNow;
    private float amount;
    private final float payable;


    public ChequeCreation(String chequeFilePath, float cardDiscount, HashMap<Integer, Item> itemsMap) {
        this.chequeFilePath = chequeFilePath;
        this.cardDiscount = cardDiscount;
        this.itemsMap = itemsMap;
        dateToday = ChequeCreation.getDateToday();
        timeNow = ChequeCreation.getTimeToday();
        calculateAmount();
        payable = calculatePayable();
    }

    public Cheque createCheque() {
        return new ChequeBuilder()
                .setFilePath(chequeFilePath)
                .setTime(timeNow)
                .setDate(dateToday)
                .setItems(convertItemsIntoString())
                .setAmount(amount)
                .setCardDiscount(cardDiscount)
                .setPayable(payable)
                .createCheque();
    }

    private void calculateAmount() {
        final Set<Integer> idArrayOfItemsMap = itemsMap.keySet();
        Item item;
        amount = 0;
        for(Integer id : idArrayOfItemsMap) {
            item = itemsMap.get(id);
            amount += item.getPayable();
        }
    }

    private float calculatePayable() {
        final int MAX_INTEREST = 100;
        return amount * (MAX_INTEREST - cardDiscount) / MAX_INTEREST;
    }

    private String convertItemsIntoString() {
        ConverterItemsMapIntoString translatorToString = new ConverterItemsMapIntoString();
        return translatorToString.translate(itemsMap);
    }

    public static String getDateToday() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        return  day + "/" + month + "/" + year;
    }

    public static String getTimeToday() {
        LocalTime timeNaw = LocalTime.now();
        int hour = timeNaw.getHour();
        int minute = timeNaw.getMinute();
        int second = timeNaw.getSecond();
        return hour + ":" + minute + ":" + second;
    }
}