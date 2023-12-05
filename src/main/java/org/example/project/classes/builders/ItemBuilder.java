package org.example.project.classes.builders;

import org.example.project.classes.Item;
import org.example.project.classes.ItemStatus;

public class ItemBuilder {
    private int id;
    private String title;
    private ItemStatus status;
    private float price;
    private int quantity = 0;

    public ItemBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItemBuilder setStatus(ItemStatus status) {
        this.status = status;
        return this;
    }

    public ItemBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ItemBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Item createItem() {
        return new Item(id, title, status, price, quantity);
    }
}