package org.example.project.command.chequeCreation.converters;

import org.example.project.classes.Item;
import org.example.project.classes.ItemStatus;
import org.example.project.classes.builders.ItemBuilder;
import org.junit.jupiter.api.*;
import org.example.project.command.chequeCreation.converters.ConverterStringArrayIntoItemsMap;

import java.util.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConverterStringArrayIntoItemsMapTest {

    private String[] stringArray;
    private long cardNumber;
    private ConverterStringArrayIntoItemsMap converter;

    @BeforeAll
    void createTranslatorTest() {
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
        ArrayList<Item> fileItems = new ArrayList<Item>();
        fileItems.add(item1);
        fileItems.add(item2);
        fileItems.add(item3);
        fileItems.add(item4);
        converter = new ConverterStringArrayIntoItemsMap(fileItems);
    }

    @Test
    void returnMapFromStringArray() {
        stringArray = new String[8];
        stringArray[0] = "4-875";
        stringArray[1] = "3-3";
        stringArray[2] = "2-46";
        stringArray[3] = "3-78357";
        stringArray[4] = "3-45";
        stringArray[5] = "caRd-567983554";
        stringArray[6] = "756-65453652";
        stringArray[7] = "3-0";
        cardNumber = 567983554;
        HashMap<Integer, Item> expectedMap = new HashMap<>();
        Item item1 = new ItemBuilder()
                .setId(756)
                .setPrice(6.34f)
                .setStatus(ItemStatus.NON_PROMOTIONAL)
                .setTitle("first")
                .setQuantity(65453652)
                .createItem();
        Item item2 = new ItemBuilder()
                .setId(2)
                .setPrice(4.23f)
                .setStatus(ItemStatus.PROMOTIONAL)
                .setTitle("second")
                .setQuantity(46)
                .createItem();
        Item item3 = new ItemBuilder()
                .setId(3)
                .setPrice(15.76f)
                .setStatus(ItemStatus.NON_PROMOTIONAL)
                .setTitle("third")
                .setQuantity(78405)
                .createItem();
        Item item4 = new ItemBuilder()
                .setId(4)
                .setPrice(0.43f)
                .setStatus(ItemStatus.PROMOTIONAL)
                .setTitle("forth")
                .setQuantity(875)
                .createItem();
        expectedMap.put(4, item4);
        expectedMap.put(3, item3);
        expectedMap.put(2, item2);
        expectedMap.put(756, item1);
        HashMap<Integer, Item> actualMap = converter.translate(stringArray);
        Assertions.assertTrue(compareMap(actualMap, expectedMap), "returnMapFromStringArray() is failed");
    }

    private boolean compareMap(HashMap<Integer, Item> actualMap, HashMap<Integer, Item> expectedMap) {
        Set<Integer> keys = actualMap.keySet();
        if(keys.equals(expectedMap.keySet())){
            for (Integer id : keys) {
                if (!actualMap.get(id).equals(expectedMap.get(id))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Test
    void returnCardNumber() {
        Assertions.assertEquals(converter.getCardNumber(), cardNumber, "returnCardNumber() is failed");
    }

    @Test
    void returnOneFromMoreThenOneCards() {
        stringArray = new String[8];
        stringArray[0] = "4-875";
        stringArray[1] = "3-3";
        stringArray[2] = "card-460875";
        stringArray[3] = "3-78357";
        stringArray[4] = "3-45";
        stringArray[5] = "caRd-567983554";
        stringArray[6] = "2-65453652";
        stringArray[7] = "card-96784939";
        cardNumber = 460875;
        converter.translate(stringArray);
        Assertions.assertEquals(converter.getCardNumber(), cardNumber, "returnOneFromMoreThenOneCards() is failed");
    }

    @Test
    void returnIfOnlyCard() {
        stringArray = new String[1];
        stringArray[0] = "card-375453642";
        cardNumber = 375453642;
        converter.translate(stringArray);
        Assertions.assertEquals(converter.getCardNumber(), cardNumber, "returnIfOnlyCard() if failed");
    }
}
