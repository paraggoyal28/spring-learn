package org.example.Behavioral.Command;

public class TurnACOnCommand implements ICommand {

    AirConditioner ac;

    TurnACOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOn();
    }

    @Override
    public void undo() {
        ac.turnOff();
    }
}
