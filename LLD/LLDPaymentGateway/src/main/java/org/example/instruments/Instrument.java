package org.example.instruments;

public abstract class Instrument {
    int instrumentID;
    int userID;
    InstrumentType instrumentType;

    public int getInstrumentID() {
        return instrumentID;
    }

    public int getUserID() {
        return userID;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
}
