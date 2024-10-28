package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Invoice;

public interface IPaymentService extends ServiceInterface{

    void generateInvoice();

    private void generateInvoiceView(Invoice invoice) {}

    private void handleContinueOption(){}

}
