package com.tauseef.app.repositories;

import com.tauseef.app.TestCase;
import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InvoiceRepositoryTest extends TestCase {

    private InvoiceRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InvoiceRepository();
    }

    @Test
    void create() {
        Invoice invoice = createInvoice();
        repo.create(invoice);
        assertNotNull(repo.getInvoices());
        assertEquals(invoice, repo.getInvoices().getFirst());
    }

    @Test
    void findByAppointment() {

        Appointment appointment = createAppointment();
        Invoice invoice = createInvoice(null, appointment);
        repo.create(invoice);

        Invoice invoice2 = createInvoice();
        repo.create(invoice2);

        assertEquals(invoice, repo.findByAppointment(appointment));
    }
}