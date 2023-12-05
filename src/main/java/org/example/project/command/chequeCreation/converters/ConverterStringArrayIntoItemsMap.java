package org.example.project.command.chequeCreation.converters;

/**
 * new className(fileItemsMap)
 * .translate(HashMap<Integer, Integer> itemMap from class TranslateStringToIntMap)
 * return finalItemMap
 */

import java.util.*;

import org.example.project.classes.builders.ItemBuilder;
import org.example.project.classes.Item;

public class ConverterStringArrayIntoItemsMap implements TypeConverter<String[], HashMap<Integer, Item>> {

    private final HashMap<Integer, Integer> intMap;
    private final HashMap<Integer, Item> fileItemsMap;
    private final HashMap<Integer, Item> resultItemsMap;
    private long cardNumber = 0;

    public ConverterStringArrayIntoItemsMap(final ArrayList<Item> fileItemsInformation) {
        this.fileItemsMap = translate(fileItemsInformation);
        resultItemsMap = new HashMap<>();
        intMap = new HashMap<>();
    }

    @Override
    public HashMap<Integer, Item> translate(final String[] inputInformation) {
        intMap.clear();
        resultItemsMap.clear();
        cardNumber = 0;
        for(String item : inputInformation) {
            writeItemInIntMap(item);
        }
        writeItemsInformation();
        return resultItemsMap;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    private HashMap<Integer, Item> translate(ArrayList<Item> fileItemsInformation) {
        HashMap<Integer, Item> result = new HashMap<>();
        for(Item item : fileItemsInformation) {
            result.put(item.getId(), item);
        }
        return result;
    }

    private void writeItemInIntMap(final String itemString) {
        final int id, quantity;
        final int hyphenIndex = itemString.indexOf('-');
        if(!checkItIsACardNumber(itemString)) {
            id = Integer.parseInt(itemString.substring(0, hyphenIndex));
            quantity = Integer.parseInt(itemString.substring(hyphenIndex + 1));
            writeNumbersInIntMap(id, quantity);
        }
    }

    private void writeNumbersInIntMap(int id, int quantity) {
        if(intMap.containsKey(id)) {
            Integer existingQuantity = intMap.get(id);
            existingQuantity += quantity;
            intMap.replace(id, existingQuantity);
        } else {
            intMap.put(id, quantity);
        }
    }

    private boolean checkItIsACardNumber(String string) {
        string = string.toLowerCase();
        if(string.startsWith("card-")) {
            if(cardNumber == 0) {
                cardNumber = Long.parseLong(string.replace("card-", ""));
            } else {
                System.out.println("You can Use only one discount card!");
            }
            return true;
        }
        return false;
    }

    private void writeItemsInformation() {
        final Set<Integer> idArrayOfIntMap = intMap.keySet();
        Item item;
        for(Integer id : idArrayOfIntMap) {
            item = buildItem(id);
            if(item != null){
                resultItemsMap.put(id, item);
            }
        }
    }

    private Item buildItem(Integer id){
        final Item fileItem = fileItemsMap.get(id);
        Item item = null;
        if(fileItem != null) {
            item = new ItemBuilder()
                    .setId(fileItem.getId())
                    .setTitle(fileItem.getTitle())
                    .setPrice(fileItem.getPrice())
                    .setStatus(fileItem.getStatus())
                    .setQuantity(intMap.get(id))
                    .createItem();
        } else {
            System.out.println("Item with id " + id + " not found in item's list");
        }
        return item;
    }
}
