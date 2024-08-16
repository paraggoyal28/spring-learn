package org.example.Structural.Composite.fileSystem;

public class File implements FileSystem {
    String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    public void ls() {
        System.out.println("file name " + fileName);
    }
}
