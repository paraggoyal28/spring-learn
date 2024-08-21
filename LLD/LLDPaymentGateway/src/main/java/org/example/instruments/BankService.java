package org.example.instruments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankService extends InstrumentService {

    @Override
    public InstrumentDO addInstrument(InstrumentDO instrumentDO) {
        // Bank specific logic here
        BankInstrument bankInstrument = new BankInstrument();
        bankInstrument.instrumentID = new Random().nextInt(90)+10;
        bankInstrument.bankAccountNumber = instrumentDO.bankAccountNumber;
        bankInstrument.ifscCode = instrumentDO.ifsc;
        bankInstrument.instrumentType = InstrumentType.BANK;
        bankInstrument.userID = instrumentDO.userID;
        List<Instrument> userInstrumentsList = userVsInstruments.computeIfAbsent(bankInstrument.userID, k -> new ArrayList<>());

        userInstrumentsList.add(bankInstrument);
        return mapBankInstrumentToInstrumentDO(bankInstrument);
    }

    private InstrumentDO mapBankInstrumentToInstrumentDO(BankInstrument bankInstrument) {
        InstrumentDO instrumentDOObj = new InstrumentDO();
        instrumentDOObj.instrumentType = bankInstrument.instrumentType;
        instrumentDOObj.instrumentID = bankInstrument.instrumentID;
        instrumentDOObj.bankAccountNumber = bankInstrument.bankAccountNumber;
        instrumentDOObj.ifsc = bankInstrument.ifscCode;
        instrumentDOObj.userID = bankInstrument.userID;
        return instrumentDOObj;
    }

    @Override
    public List<InstrumentDO> getInstrumentsByUserID(int userID) {
        List<Instrument> userInstruments = userVsInstruments.get(userID);
        List<InstrumentDO> userInstrumentsFetched = new ArrayList<>();
        for(Instrument instrument : userInstruments) {
            if(instrument.getInstrumentType() == InstrumentType.BANK)
                userInstrumentsFetched.add(mapBankInstrumentToInstrumentDO((BankInstrument) instrument));
        }
        return userInstrumentsFetched;
    }
}
