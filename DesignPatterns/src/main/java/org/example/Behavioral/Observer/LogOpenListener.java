package org.example.Behavioral.Observer;

import java.io.File;

public class LogOpenListener implements EventListener {
    private final File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }


    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + " : Someone has perfomed " + eventType + " operation with the following file: " + file.getName());
    }
}
