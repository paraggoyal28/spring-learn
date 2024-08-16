package org.example.Structural.Bridge.remoteDevice;

import org.example.Structural.Bridge.remoteDevice.devices.Device;
import org.example.Structural.Bridge.remoteDevice.devices.Radio;
import org.example.Structural.Bridge.remoteDevice.devices.Tv;
import org.example.Structural.Bridge.remoteDevice.remotes.AdvancedRemote;
import org.example.Structural.Bridge.remoteDevice.remotes.BasicRemote;

public class Demo {

    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    private static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();


        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }


}
