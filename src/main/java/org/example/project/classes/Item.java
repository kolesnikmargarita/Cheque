package org.example.project.classes;

import java.io.Serializable;

public class Item implements Serializable {

    private final int id;
    private final String title;
    private final ItemStatus status;
    private final float price;
    private final int quantity;
    private float discount;
    private float payable;

    public Item(int id, String title, ItemStatus status, float price, int quantity) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        calculateDiscount();
        calculatePayable();
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getDiscount() {
        return discount;
    }
    public float getPayable() {
        return payable;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return id == item.getId()
                && title.equals(item.getTitle())
                && status.equals(item.getStatus())
                && price == item.getPrice()
                && quantity == item.getQuantity()
                && discount == item.getDiscount()
                && payable == item.getPayable();
    }

    private void calculateDiscount(){
        final float ABLE_DISCOUNT = 10f;
        final int MIN_DISCOUNT_QUANTITY = 6;
        if(quantity >= MIN_DISCOUNT_QUANTITY && status == ItemStatus.PROMOTIONAL){
            discount = ABLE_DISCOUNT;
        } else {
            discount = 0;
        }
    }

    private void calculatePayable() {
        final int MAX_INTEREST = 100;
        payable = price * quantity * (MAX_INTEREST - discount) / MAX_INTEREST;
    }
}