package org.example.Behavioral.Interpreter.BinaryOperations;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<String, Integer> contextMap = new HashMap<>();

    public void put(String stringVariable, int intValue) {
        contextMap.put(stringVariable, intValue);
    }

    public int get(String strVariable) {
        return contextMap.get(strVariable);
    }
}
