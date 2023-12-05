package org.example.project.classes;

import org.example.project.classes.Cheque;
import org.example.project.classes.builders.ChequeBuilder;
import org.example.project.command.ChequeInformation;
import org.example.project.command.chequeCreation.ChequeCreation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChequeTest {

    private final String filePath = ChequeInformation.getChequeFilePath();
    private Cheque cheque;
    private String date;
    private String time;

    @BeforeEach
    void setUp() {
        date = ChequeCreation.getDateToday();
        time = ChequeCreation.getTimeToday();
        cheque = new ChequeBuilder()
                .setFilePath(filePath)
                .setCardDiscount(0)
                .setDate(date)
                .setTime(time)
                .setItems("")
                .setAmount(0)
                .setPayable(0)
                .createCheque();
    }

    @Test
    void writeInFile() {
        String expected = "Cheque saved in file";
        assertEquals(cheque.writeInFile(), expected, "Impossible write cheque in file!");
    }
}