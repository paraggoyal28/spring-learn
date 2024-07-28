package org.example.Behavioral.ChainOfResponsibility.logger;

public class InfoLogProcessor extends LogProcessor {

    InfoLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if(logLevel == INFO) {
            System.out.println("INFO: " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}
