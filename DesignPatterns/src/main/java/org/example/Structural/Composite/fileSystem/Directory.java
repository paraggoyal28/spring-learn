package org.example.Structural.Composite.fileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem {

    String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String name) {
        this.directoryName = name;
        fileSystemList = new ArrayList<>();
    }

    public void add(FileSystem fileSystem) {
        fileSystemList.add(fileSystem);
    }

    public void ls() {
        System.out.println("Directory name " + directoryName);
        for(FileSystem fileSystem: fileSystemList) {
            fileSystem.ls();
        }
    }
}
