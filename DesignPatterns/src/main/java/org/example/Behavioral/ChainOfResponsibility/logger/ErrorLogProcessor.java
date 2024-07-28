package org.example.Behavioral.ChainOfResponsibility.logger;

public class ErrorLogProcessor extends LogProcessor {

    ErrorLogProcessor(LogProcessor logProcessor) {
        super(logProcessor);
    }

    @Override
    public void log(int logLevel, String message) {
        if(logLevel == ERROR) {
            System.out.println("ERROR: " + message);
        } else {
            super.log(logLevel, message);
        }
    }
}
