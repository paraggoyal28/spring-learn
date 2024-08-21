package org.example.instruments;

import java.util.List;

public class InstrumentController {
    InstrumentServiceFactory instrumentServiceFactory;

    public InstrumentController() {
        this.instrumentServiceFactory = new InstrumentServiceFactory();
    }

    public InstrumentDO addInstrument(InstrumentDO instrument) {
        InstrumentService instrumentService = instrumentServiceFactory.getInstrumentService(
                instrument.getInstrumentType());
        return instrumentService.addInstrument(instrument);
    }

    public List<InstrumentDO> getAllInstruments(int userID) {
        InstrumentService bankInstrumentService = instrumentServiceFactory.getInstrumentService(
                InstrumentType.BANK);
        InstrumentService cardInstrumentService = instrumentServiceFactory.getInstrumentService(
                InstrumentType.CARD);
        List<InstrumentDO> instrumentDOList = bankInstrumentService.getInstrumentsByUserID(userID);
        instrumentDOList.addAll(cardInstrumentService.getInstrumentsByUserID(userID));
        return instrumentDOList;
    }

    public InstrumentDO getInstrumentByID(int userID, int instrumentID) {
        List<InstrumentDO> instrumentDOList = getAllInstruments(userID);
        for(InstrumentDO instrumentDO : instrumentDOList) {
            if (instrumentDO.getInstrumentID() == instrumentID) {
                return instrumentDO;
            }
        }
        return null;
    }
}
