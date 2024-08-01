package org.example.Behavioral.Interpreter.AddOperation;

public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.put("a", 2);
        context.put("b", 4);

        AbstractExpression expression = new MultiplyNonTerminalExpression(
                new NumberTerminalExpression("a"), new NumberTerminalExpression("b")
        );
        System.out.println(expression.interpret(context));
    }
}
