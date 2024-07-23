package org.example.Behavioral.Command;

// Receiver
public class AirConditioner {
    boolean isOn;
    int temperature;

    public void turnOn() {
        isOn = true;
        System.out.println("AC is on");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("AC is off");
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("Temperature changed to: " + temperature);
    }
}
