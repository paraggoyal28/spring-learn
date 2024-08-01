package org.example.Behavioral.Interpreter.AddOperation;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<String, Integer> contextMap = new HashMap<>();

    public void put(String strVariable, int intValue) {
        contextMap.put(strVariable, intValue);
    }

    public int get(String strVariable) {
        return contextMap.get(strVariable);
    }
}
