package org.example.instruments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardService extends InstrumentService {
    @Override
    public InstrumentDO addInstrument(InstrumentDO instrumentDO) {
        // card specific logic here
        CardInstrument cardInstrument = new CardInstrument();
        cardInstrument.instrumentID = new Random().nextInt(90) + 10;
        cardInstrument.cardNumber = instrumentDO.cardNumber;
        cardInstrument.cvvNumber = instrumentDO.cvv;
        cardInstrument.instrumentType = InstrumentType.CARD;
        cardInstrument.userID = instrumentDO.userID;
        List<Instrument> userInstrumentsList = userVsInstruments.computeIfAbsent(cardInstrument.userID, k -> new ArrayList<>());
        userInstrumentsList.add(cardInstrument);
        return mapCardInstrumentToInstrumentDO((CardInstrument) cardInstrument);
    }

    private InstrumentDO mapCardInstrumentToInstrumentDO(CardInstrument cardInstrument) {
        InstrumentDO instrumentDO= new InstrumentDO();
        instrumentDO.instrumentType = cardInstrument.instrumentType;
        instrumentDO.instrumentID = cardInstrument.instrumentID;
        instrumentDO.cardNumber = cardInstrument.cardNumber;
        instrumentDO.cvv =  cardInstrument.cvvNumber;
        instrumentDO.userID = cardInstrument.userID;
        return instrumentDO;
    }

    @Override
    public List<InstrumentDO> getInstrumentsByUserID(int userID) {
        List<Instrument> userInstruments = userVsInstruments.get(userID);
        List<InstrumentDO> userInstrumentsFetched = new ArrayList<>();
        for (Instrument instrument : userInstruments) {
            if (instrument.getInstrumentType() == InstrumentType.CARD) {
                userInstrumentsFetched.add(mapCardInstrumentToInstrumentDO((CardInstrument) instrument));
            }
        }
        return userInstrumentsFetched;
    }
}
