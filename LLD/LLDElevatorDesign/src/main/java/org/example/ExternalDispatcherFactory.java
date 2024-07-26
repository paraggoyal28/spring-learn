package org.example;

public class ExternalDispatcherFactory {

    public static ExternalDispatcher getExternalDispatcher(DispatcherType dispatcherType) {
        if(dispatcherType.equals(DispatcherType.FIXED)) {
             return FixedExternalDispatcher.getInstance();
        }
        return EvenOddExternalDispatcher.getInstance();
    }
}
