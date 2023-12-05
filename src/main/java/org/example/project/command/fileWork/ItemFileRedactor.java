package org.example.project.command.fileWork;

import org.example.project.classes.builders.ItemBuilder;
import org.example.project.classes.Item;
import org.example.project.classes.ItemStatus;

import java.io.*;
import java.util.ArrayList;

public class ItemFileRedactor extends FileRedactor {

    public ItemFileRedactor(String filePath) {
        super(filePath);
    }

    @Override
    public String write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, false))) {
            Item item1 = new ItemBuilder()
                    .setId(756)
                    .setPrice(6.34f)
                    .setStatus(ItemStatus.NON_PROMOTIONAL)
                    .setTitle("first")
                    .createItem();
            Item item2 = new ItemBuilder()
                    .setId(2)
                    .setPrice(4.23f)
                    .setStatus(ItemStatus.PROMOTIONAL)
                    .setTitle("second")
                    .createItem();
            Item item3 = new ItemBuilder()
                    .setId(3)
                    .setPrice(15.76f)
                    .setStatus(ItemStatus.NON_PROMOTIONAL)
                    .setTitle("third")
                    .createItem();
            Item item4 = new ItemBuilder()
                    .setId(4)
                    .setPrice(0.43f)
                    .setStatus(ItemStatus.PROMOTIONAL)
                    .setTitle("forth")
                    .createItem();
            oos.writeObject(item1);
            oos.writeObject(item2);
            oos.writeObject(item3);
            oos.writeObject(item4);
            return "File Items.dat rewrite successfully";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public ArrayList<Item> read() throws IOException{
        final int FILE_ITEMS_QUANTITY = 4;
        ArrayList<Item> itemsArray = new ArrayList<Item>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            for(int i = 0; i < FILE_ITEMS_QUANTITY; i++) {
                itemsArray.add((Item)ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return itemsArray;
    }
}