package org.example.project.command;

public class Redactor {

    private final Command createCheque;
    private final Command showItemsFile;
    private final Command showDiscountCardsFile;
    private final Command updateFiles;

    public Redactor(Command createCheque, Command showItemsFile, Command showDiscountCardsFile, Command updateFile) {
        this.createCheque = createCheque;
        this.showItemsFile = showItemsFile;
        this.showDiscountCardsFile = showDiscountCardsFile;
        this.updateFiles = updateFile;

    }

    public void createChequeInformation() {
        createCheque.execute();
    }

    public void showItemsFileInformation() {
        showItemsFile.execute();
    }

    public void showDiscountCardInformation() {
        showDiscountCardsFile.execute();
    }
    public void updateFileInformation() {
        updateFiles.execute();
    }
}
