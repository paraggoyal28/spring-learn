package org.example.ATMStates;

import org.example.ATM;
import org.example.Card;

public class CheckBalanceState extends ATMState {
    public CheckBalanceState() {

    }

    @Override
    public void displayBalance(ATM atm, Card card) {
        System.out.println("Your Balance is : " + card.getBankBalance());
        exit(atm);
    }
}
