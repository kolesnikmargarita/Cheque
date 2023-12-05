package org.example.project.command.fileWork;

import java.io.IOException;
import java.util.ArrayList;

public abstract class FileRedactor {

    protected String filePath;

    protected FileRedactor(String filePath){
        this.filePath = filePath;
    }
    public abstract String write();
    public abstract ArrayList read() throws IOException;
}
