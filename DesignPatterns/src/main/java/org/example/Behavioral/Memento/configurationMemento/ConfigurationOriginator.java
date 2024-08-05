package org.example.Behavioral.Memento.configurationMemento;

public class ConfigurationOriginator {
    private int height;
    private int width;

    public ConfigurationOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ConfigurationMemento createMemento() {
        return new ConfigurationMemento(height, width);
    }

    public void restoreMemento(ConfigurationMemento memento) {
        this.height = memento.getHeight();
        this.width = memento.getWidth();
    }
}
