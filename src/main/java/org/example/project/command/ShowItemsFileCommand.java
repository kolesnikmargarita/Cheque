package org.example.project.command;

import org.example.project.classes.Item;

import java.util.ArrayList;

public class ShowItemsFileCommand implements Command {

    ChequeInformation chequeInformation;

    public ShowItemsFileCommand(ChequeInformation chequeInformation) {
        this.chequeInformation = chequeInformation;
    }

    @Override
    public void execute() {
        printItemsFile(chequeInformation.showItemsFile());
    }

    public static void printItemsFile(ArrayList<Item> items) {
        if(items == null) {
            return;
        }
        for (Item item : items) {
            System.out.println("id: " + item.getId());
            System.out.println("description: " + item.getTitle());
            System.out.println("price: " + item.getPrice());
            System.out.println("status: " + item.getStatus());
        }
    }
}
