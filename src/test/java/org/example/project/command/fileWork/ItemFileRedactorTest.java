package org.example.project.command.fileWork;

import org.example.project.command.ChequeInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.project.command.fileWork.ItemFileRedactor;

import java.io.IOException;

public class ItemFileRedactorTest {

    @Test
    void NotNullIfThereIsClassItem() throws IOException {
        ItemFileRedactor redactor = new ItemFileRedactor(ChequeInformation.getItemsFilePath());
        Assertions.assertNotNull(redactor.read(), "Class Item is not fix!");
    }
}
