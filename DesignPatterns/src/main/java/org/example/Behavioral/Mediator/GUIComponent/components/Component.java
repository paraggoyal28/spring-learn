package org.example.Behavioral.Mediator.GUIComponent.components;

import org.example.Behavioral.Mediator.GUIComponent.mediator.Mediator;

public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
