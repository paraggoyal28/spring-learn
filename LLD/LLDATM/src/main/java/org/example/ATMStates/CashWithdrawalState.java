package org.example.ATMStates;

import org.example.ATM;
import org.example.AmountWithdrawal.CashWithdrawProcessor;
import org.example.AmountWithdrawal.FiveHundredWithdrawProcessor;
import org.example.AmountWithdrawal.OneHundredWithdrawProcessor;
import org.example.AmountWithdrawal.TwoThousandWithdrawProcessor;
import org.example.Card;

public class CashWithdrawalState extends ATMState {
    public CashWithdrawalState() {
        System.out.println("Please enter the withdrawal amount");
    }

    public void cashWithdrawal(ATM atm, Card card, int withdrawalAmountRequest) {
        if(atm.getAtmBalance() < withdrawalAmountRequest) {
            System.out.println("Insufficient fund in the ATM machine");
            exit(atm);
        } else if(card.getBankBalance() < withdrawalAmountRequest) {
            System.out.println("Insufficient fund in your Bank account");
            exit(atm);
        } else {
            card.deductBankBalance(withdrawalAmountRequest);
            atm.deductATMBalance(withdrawalAmountRequest);

            // using chain of responsibility for this logic, how many 2k Rs notes
            // how many 500 Rs. notes, has to be withdrawn
            CashWithdrawProcessor withdrawProcessor =
                    new TwoThousandWithdrawProcessor(new FiveHundredWithdrawProcessor(new OneHundredWithdrawProcessor(null)));

            withdrawProcessor.withdraw(atm, withdrawalAmountRequest);
            exit(atm);
        }
    }
}
