package org.example.Behavioral.Memento.configurationMemento;

public class Client {
    public static void main(String[] args) {
        ConfigurationCareTaker careTaker = new ConfigurationCareTaker();

        ConfigurationOriginator originator = new ConfigurationOriginator(5, 10);

        // save it
        ConfigurationMemento snapshot1 = originator.createMemento();

        // add it to history
        careTaker.addMemento(snapshot1);

        // originator changing to new state

        originator.setHeight(7);
        originator.setWidth(14);

        ConfigurationMemento snapshot2 = originator.createMemento();

        // add it to history
        careTaker.addMemento(snapshot2);

        // originator changing to new state
        originator.setHeight(9);
        originator.setWidth(18);

        ConfigurationMemento restoreStateMemento = careTaker.undo();
        originator.restoreMemento(restoreStateMemento);

        System.out.println("height:  " + originator.getHeight() + " width: " + originator.getWidth());
    }
}
