package org.example.Behavioral.Command;

public class TurnACOffCommand implements ICommand {
    AirConditioner ac;

    TurnACOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.turnOff();
    }

    @Override
    public void undo() {
        ac.turnOn();
    }
}
