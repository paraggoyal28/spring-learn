package org.example.Behavioral.ChainOfResponsibility.logger;

public class Client {
    public static void main(String[] args) {
        LogProcessor logProcessor = new DebugLogProcessor(new InfoLogProcessor(new ErrorLogProcessor(null)));

        logProcessor.log(LogProcessor.ERROR, "exception happened");
        logProcessor.log(LogProcessor.INFO, "just for info");
        logProcessor.log(LogProcessor.DEBUG, "need to debug this");
    }
}
