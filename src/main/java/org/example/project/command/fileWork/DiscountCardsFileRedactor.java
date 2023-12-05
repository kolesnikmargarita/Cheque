package org.example.project.command.fileWork;

import org.example.project.classes.DiscountCard;

import java.io.*;
import java.util.ArrayList;

public class DiscountCardsFileRedactor extends FileRedactor {

    public DiscountCardsFileRedactor(String filePath) {
        super(filePath);
    }

    @Override
    public String write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath, false))) {
            DiscountCard p = new DiscountCard(12345L, 3f);
            DiscountCard p2 = new DiscountCard(54321012345L, 2.5f);
            oos.writeObject(p);
            oos.writeObject(p2);
            return "File discountCards.dat rewrite successfully";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public ArrayList<DiscountCard> read() throws IOException {
        final int FILE_ITEMS_QUANTITY = 2;
        ArrayList<DiscountCard> discountCardsArray= new ArrayList<DiscountCard>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            for(int i = 0; i < FILE_ITEMS_QUANTITY; i++){
                discountCardsArray.add((DiscountCard) ois.readObject());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return discountCardsArray;
    }
}
