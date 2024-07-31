package org.example.AmountWithdrawal;


import org.example.ATM;

public class TwoThousandWithdrawProcessor extends CashWithdrawProcessor {


    public TwoThousandWithdrawProcessor(CashWithdrawProcessor cashWithdrawProcessor) {
        super(cashWithdrawProcessor);
    }

    public void withdraw(ATM atm, int remainingAmount) {
        int required = remainingAmount/2000;
        int balance = remainingAmount%2000;

        if(required <= atm.getNoOfTwoThousandNotes()) {
            atm.deductTwoThousandNotes(required);
        } else {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            balance = balance + (required - atm.getNoOfTwoThousandNotes()) * 2000;
        }

        if(balance != 0) {
            super.withdraw(atm, balance);
        }
    }
}
