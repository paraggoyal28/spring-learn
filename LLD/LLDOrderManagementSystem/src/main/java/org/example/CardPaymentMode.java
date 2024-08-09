package org.example;

public class CardPaymentMode implements PaymentMode {

    @Override
    public boolean makePayment() {
        return true;
    }
}
