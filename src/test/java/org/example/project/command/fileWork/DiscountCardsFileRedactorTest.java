package org.example.project.command.fileWork;

import org.example.project.command.ChequeInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.project.command.fileWork.DiscountCardsFileRedactor;

import java.io.IOException;

public class DiscountCardsFileRedactorTest {

    @Test
    void NotNullIfThereIsClassDiscountCard() throws IOException {
        DiscountCardsFileRedactor redactor = new DiscountCardsFileRedactor(ChequeInformation.getDiscountCardsFilePath());
        Assertions.assertNotNull(redactor.read(), "Class DiscountCard is not fix!");
    }
}
