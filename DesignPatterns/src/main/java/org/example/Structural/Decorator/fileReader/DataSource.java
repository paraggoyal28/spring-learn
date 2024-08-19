package org.example.Structural.Decorator.fileReader;

public interface DataSource {
    void writeData(String data);
    String readData();
}
