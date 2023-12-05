package org.example.project.command.chequeCreation.converters;

import org.example.project.classes.Item;

import java.util.*;

public class ConverterItemsMapIntoString implements TypeConverter<HashMap<Integer, Item>, String> {

    @Override
    public String translate(HashMap<Integer, Item> inputInformation) {
        String result = "";
        final Set<Integer> idArrayOfItemsMap = inputInformation.keySet();
        Item item;
        float discount;
        for(Integer id : idArrayOfItemsMap) {
            item = inputInformation.get(id);
            result += "\n" + item.getQuantity() + "  " + item.getTitle() + " ";
            discount = item.getDiscount();
            if(discount != 0) {
                result += " -" + discount + "% ";
            } else {
                result += "       ";
            }
            result += "  $" + item.getPrice() + "  $" + item.getPayable();
        }
        return result;
    }
}
