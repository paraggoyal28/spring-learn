package org.example.transactions;

import org.example.instruments.InstrumentDO;

public class Processor {
    public void processPayment(InstrumentDO senderInstrumentDO, InstrumentDO receiverInstrumentDO) {
        System.out.println("Sender Instrument DO " + senderInstrumentDO.getInstrumentID());
        System.out.println("Payment Processed");
        System.out.println("Receiver Instrument DO " + receiverInstrumentDO.getInstrumentID());
    }
}
