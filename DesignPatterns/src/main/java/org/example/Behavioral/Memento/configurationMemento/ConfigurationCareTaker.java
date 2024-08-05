package org.example.Behavioral.Memento.configurationMemento;


import java.util.ArrayList;
import java.util.List;

public class ConfigurationCareTaker {
    List<ConfigurationMemento> history = new ArrayList<>();

    public void addMemento(ConfigurationMemento memento) {
        history.add(memento);
    }

    public ConfigurationMemento undo() {
        if(!history.isEmpty()) {
            int lastMementoIndex = history.size() - 1;

            ConfigurationMemento lastMemento = history.get(lastMementoIndex);

            history.remove(lastMementoIndex);

            return lastMemento;
        }

        return null;
    }
}
