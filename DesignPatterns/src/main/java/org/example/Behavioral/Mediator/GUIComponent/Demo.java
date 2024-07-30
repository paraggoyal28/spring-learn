package org.example.Behavioral.Mediator.GUIComponent;

import org.example.Behavioral.Mediator.GUIComponent.components.*;
import org.example.Behavioral.Mediator.GUIComponent.mediator.Editor;
import org.example.Behavioral.Mediator.GUIComponent.mediator.Mediator;

import javax.swing.*;


public class Demo {
    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
