package org.example.project.classes.builders;

import org.example.project.classes.Cheque;

public class ChequeBuilder {
    private String filePath;
    private String time;
    private String date;
    private String items;
    private float amount;
    private float cardDiscount;
    private float payable;

    public ChequeBuilder setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public ChequeBuilder setTime(String time) {
        this.time = time;
        return this;
    }

    public ChequeBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public ChequeBuilder setItems(String items) {
        this.items = items;
        return this;
    }

    public ChequeBuilder setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public ChequeBuilder setCardDiscount(float cardDiscount) {
        this.cardDiscount = cardDiscount;
        return this;
    }

    public ChequeBuilder setPayable(float payable) {
        this.payable = payable;
        return this;
    }

    public Cheque createCheque() {
        return new Cheque(filePath, time, date, items, amount, cardDiscount, payable);
    }
}