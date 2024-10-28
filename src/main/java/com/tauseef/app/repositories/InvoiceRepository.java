package com.tauseef.app.repositories;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRepository {

    private final List<Invoice> invoices;

    public InvoiceRepository() {
        invoices = new ArrayList<>();
    }

    public Invoice create(Invoice invoice) {
        invoices.add(invoice);
        return invoice;
    }

    public Invoice findByAppointment(Appointment appointment) {
        return getInvoices()
                .stream()
                .filter(invoice -> invoice.getAppointment().equals(appointment))
                .findFirst()
                .orElse(null);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
