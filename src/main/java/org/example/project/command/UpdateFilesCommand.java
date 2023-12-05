package org.example.project.command;

public class UpdateFilesCommand implements Command {

    ChequeInformation chequeInformation;

    public UpdateFilesCommand(ChequeInformation chequeInformation) {
        this.chequeInformation = chequeInformation;
    }

    @Override
    public void execute() {
        System.out.println(chequeInformation.updateFiles());
    }
}
