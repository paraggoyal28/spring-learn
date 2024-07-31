package org.example.ATMStates;

import org.example.ATM;
import org.example.Card;

public class HasCardState extends ATMState {
    public HasCardState() {
        System.out.println("enter your card pin number");
    }

    @Override
    public void authenticatePin(ATM atm, Card card, int pin) {
        boolean isCorrectPinEntered = card.isCorrectPINEntered(pin);

        if(isCorrectPinEntered) {
            atm.setCurrentATMState(new SelectOperationState());
        } else {
            System.out.println("Invalid PIN number");
            exit(atm);
        }
    }
}
