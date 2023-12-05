package org.example.project.classes;

import java.io.*;

public class Cheque implements Serializable {

    private final String filePath;
    private final String date;
    private final String time;
    private final String items;
    private final float amount;
    private final float cardDiscount;
    private final float payable;
    private final String resultText;

    public Cheque(String filePath, String time, String date, String items, float amount, float cardDiscount, float payable) {
        this.filePath = filePath;
        this.date = date;
        this.time = time;
        this.items = items;
        this.amount = amount;
        this.cardDiscount = cardDiscount;
        this.payable = payable;
        ChequeStructure chequeStructure = new ChequeStructure();
        resultText = chequeStructure.getStructure();
    }

    public String writeInFile() {
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(resultText);
        } catch (IOException e) {
            return (e.getMessage());
        }
        return "Cheque saved in file";
    }

    public String getResultText() {
        return resultText;
    }

    private class ChequeStructure {

        String principalTitle = "CASH RECEIPT";
        String shop = "\nSUPERMARKET 123";
        String address = "\n12, MILKYWAY Galaxy/ Earth";
        String contacts = "\nTel :123-56-7890";
        String cashier = "\nCASHIER: №1250";
        String date = "\nDATE: ";
        String time = "\nTIME: ";
        String amount = "\nAmount: ";
        String cardDiscount = "\nCardDiscount: ";
        String payable = "\nPayable: ";

        private String getStructure() {
            String chequeText;
            chequeText = principalTitle + shop + address + contacts + cashier;
            chequeText += date + Cheque.this.date;
            chequeText += time + Cheque.this.time;
            chequeText += "\n---------------------" + items + "\n--------------------";
            chequeText += amount + Cheque.this.amount;
            chequeText += cardDiscount + Cheque.this.cardDiscount;
            chequeText += payable + Cheque.this.payable;
            return chequeText;
        }
    }
}