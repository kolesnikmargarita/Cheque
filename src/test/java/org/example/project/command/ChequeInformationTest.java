package org.example.project.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.example.project.command.ChequeInformation;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChequeInformationTest {

    private ChequeInformation chequeInformation;

    @BeforeAll
    void initialized() {
        final String[] inputString = new String[0];
        chequeInformation = new ChequeInformation(inputString);


    /*inputString = {
            "3-67",
            "2-4",
            "3-56",
            "4-5",
            "2-1",
            "4-1",
            "card-12345"
    };*/
    }

    @Test
    void returnNotNullIfReedItemsFileIsPossible() {
        Assertions.assertNotNull(chequeInformation.showItemsFile(), "Impossible read items file");
    }

    @Test
    void returnNotNullIfReedDiscountCardsFileIsPossible() {
        Assertions.assertNotNull(chequeInformation.showDiscountCardsFile(), "Impossible read discountCards file");
    }
}
