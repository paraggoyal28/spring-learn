package org.example.transactions;

import org.example.instruments.InstrumentController;
import org.example.instruments.InstrumentDO;

import java.util.*;

public class TransactionService {
    public static Map<Integer, List<Transaction>> userVsTransactionsList = new HashMap<>();
    InstrumentController instrumentController;
    Processor processor;

    public TransactionService() {
        instrumentController = new InstrumentController();
        processor = new Processor();
    }

    public List<Transaction> getTransactionHistory(int userID) {
        return userVsTransactionsList.get(userID);
    }

    public TransactionDO makePayment(TransactionDO txnDO) {
        InstrumentDO senderInstrumentDO = instrumentController.getInstrumentByID(txnDO.getSenderId(), txnDO.getDebitInstrumentId());
        InstrumentDO receiverInstrumentDO = instrumentController.getInstrumentByID(txnDO.getReceiverId(), txnDO.getCreditInstrumentId());
        processor.processPayment(senderInstrumentDO, receiverInstrumentDO);
        Transaction txn = new Transaction();
        txn.setAmount(txnDO.getAmount());
        txn.setTxnID(new Random().nextInt(90) + 10);
        txn.setSenderId(txnDO.getSenderId());
        txn.setReceiverId(txnDO.getReceiverId());
        txn.setDebitInstrumentId(txnDO.getDebitInstrumentId());
        txn.setCreditInstrumentId(txnDO.getCreditInstrumentId());
        txn.setStatus(TransactionStatus.SUCCESS);
        List<Transaction> senderTxnsList = userVsTransactionsList.computeIfAbsent(txn.getSenderId(), k -> new ArrayList<>());
        senderTxnsList.add(txn);
        List<Transaction> receiverTxnLists = userVsTransactionsList.computeIfAbsent(txn.getReceiverId(), k -> new ArrayList<>());
        receiverTxnLists.add(txn);
        txnDO.setTxnID(txn.getTxnID());
        txnDO.setStatus(txn.getStatus());
        return txnDO;
    }
}
