package org.example.Behavioral.Command;

public class Client {
    public static void main(String[] args) {
        // AC Object
        AirConditioner airConditioner = new AirConditioner();

        // remote
        MyRemoteControl remoteControl = new MyRemoteControl();

        // create the command and press the button
        remoteControl.setCommand(new TurnACOnCommand(airConditioner));
        remoteControl.pressButton();

        // add the turn off command
        remoteControl.setCommand(new TurnACOffCommand(airConditioner));
        remoteControl.pressButton();

        // undo the last operation
        remoteControl.undo();
    }
}
