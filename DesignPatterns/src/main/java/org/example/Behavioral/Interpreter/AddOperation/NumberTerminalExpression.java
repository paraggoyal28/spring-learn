package org.example.Behavioral.Interpreter.AddOperation;

public class NumberTerminalExpression implements AbstractExpression {
    String stringValue;

    NumberTerminalExpression(String strValue) {
        this.stringValue = strValue;
    }

    @Override
    public int interpret(Context context) {
        return context.get(stringValue);
    }
}
