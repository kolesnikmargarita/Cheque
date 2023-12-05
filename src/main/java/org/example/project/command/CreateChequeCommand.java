package org.example.project.command;

public class CreateChequeCommand implements Command {

    ChequeInformation chequeInformation;

    public CreateChequeCommand(ChequeInformation fileInformation) {
        this.chequeInformation = fileInformation;
    }

    @Override
    public void execute() {
        String chequeText = chequeInformation.createCheque();
        System.out.println(chequeText);
    }
}