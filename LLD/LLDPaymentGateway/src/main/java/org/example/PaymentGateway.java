package org.example;

import org.example.instruments.InstrumentController;
import org.example.instruments.InstrumentDO;
import org.example.instruments.InstrumentType;
import org.example.transactions.Transaction;
import org.example.transactions.TransactionController;
import org.example.transactions.TransactionDO;
import org.example.users.UserController;
import org.example.users.UserDO;

import java.util.List;

public class PaymentGateway {
    public static void main(String[] args) {
        InstrumentController instrumentController = new InstrumentController();
        UserController userController = new UserController();
        TransactionController transactionController = new TransactionController();

        //1. add user1
        UserDO user1 = new UserDO();
        user1.setName("Sj");
        user1.setMail("sj@conceptandcoding.com");
        UserDO user1Details = userController.addUser(user1);

        //1. add user2
        UserDO user2 = new UserDO();
        user2.setName("Pj");
        user2.setMail("Pj@conceptandcoding.com");
        UserDO user2Details = userController.addUser(user2);

        // add bank to user1
        InstrumentDO bankInstrumentDO = new InstrumentDO();
        bankInstrumentDO.setBankAccountNumber("234324234324324");
        bankInstrumentDO.setInstrumentType(InstrumentType.BANK);
        bankInstrumentDO.setUserID(user1Details.getUserID());
        bankInstrumentDO.setIfsc("ER2233E");
        InstrumentDO user1BankInstrument = instrumentController.addInstrument(bankInstrumentDO);
        System.out.println("Bank Instrument created for user1: " + user1BankInstrument.getInstrumentID());

        // add card to user2
        InstrumentDO cardInstrumentDO = new InstrumentDO();
        cardInstrumentDO.setCardNumber("1230099");
        cardInstrumentDO.setInstrumentType(InstrumentType.CARD);
        cardInstrumentDO.setCvv("8916");
        cardInstrumentDO.setUserID(user2Details.getUserID());
        InstrumentDO user2CardInstrument = instrumentController.addInstrument(cardInstrumentDO);
        System.out.println("Card Instrument created for user2: " + user2CardInstrument.getInstrumentID());

        // make payment
        TransactionDO transactionDo = new TransactionDO();
        transactionDo.setAmount(10);
        transactionDo.setSenderId(user1Details.getUserID());
        transactionDo.setReceiverId(user2Details.getUserID());
        transactionDo.setDebitInstrumentId(user1BankInstrument.getInstrumentID());
        transactionDo.setCreditInstrumentId(user2CardInstrument.getInstrumentID());
        transactionController.makePayment(transactionDo);

        // get All instruments of user 1
        List<InstrumentDO> user1Instruments = instrumentController.getAllInstruments(user1Details.getUserID());
        for(InstrumentDO instrumentDO : user1Instruments) {
            System.out.println("User1 InstID: " + instrumentDO.getInstrumentID() +
                        ": UserID: " + instrumentDO.getUserID() +
                        ": InstrumentType: " + instrumentDO.getInstrumentType().name());
        }

        // get All instruments of user 2
        List<InstrumentDO> user2Instruments = instrumentController.getAllInstruments(user2Details.getUserID());
        for (InstrumentDO instrumentDO : user2Instruments) {
            System.out.println("User2 InstID: " + instrumentDO.getInstrumentID() +
                            ": UserID: " + instrumentDO.getUserID() +
                            ": InstrumentType: " + instrumentDO.getInstrumentType().name());
        }

        // get all transaction history
        List<Transaction> user1TransactionList = transactionController.getTransactionHistory(user1Details.getUserID());
        for (Transaction txn: user1TransactionList) {
            System.out.println("User1 txnID: " + txn.getTxnID() +
                        ": Amount: " + txn.getAmount() +
                        ": receiver: " + txn.getReceiverId());
        }

        List<Transaction> user2TransactionList = transactionController.getTransactionHistory(user2Details.getUserID());
        for (Transaction txn: user2TransactionList) {
            System.out.println("User2 txnID: " + txn.getTxnID() +
                            ": Amount: " + txn.getAmount() +
                            ": sender: " + txn.getSenderId());
        }



    }
}
