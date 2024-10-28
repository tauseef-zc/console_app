package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTest extends TestCase {

    @Test
    void getInvoiceDate() {
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment = createAppointment();
        Invoice invoice = createInvoice(now, appointment);

        assertEquals(now, invoice.getInvoiceDate());
    }

    @Test
    void getTotalAmount() {
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment = createAppointment();
        Invoice invoice = createInvoice(now, appointment);

        assertEquals(appointment.getFullTotal(), invoice.getTotalAmount());
    }

    @Test
    void getTaxAmount() {
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment = createAppointment();
        Invoice invoice = createInvoice(now, appointment);

        assertEquals(appointment.getTaxAmount(), invoice.getTaxAmount());
    }

    @Test
    void getAppointment() {
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment = createAppointment();
        Invoice invoice = createInvoice(now, appointment);

        assertEquals(appointment, invoice.getAppointment());
    }

    @Test
    void getId() {
        Invoice.reset();
        Invoice invoice = createInvoice();

        assertEquals(invoice.getId(), "INV001");
    }
}