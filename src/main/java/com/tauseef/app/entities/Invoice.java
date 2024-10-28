package com.tauseef.app.entities;

import java.time.LocalDateTime;

public class Invoice {

    private static int indexId = 1;
    final private String id;
    final private Appointment appointment;
    private final double taxAmount;
    private final double totalAmount;
    final private LocalDateTime invoiceDate;

    public Invoice(
            LocalDateTime invoiceDate,
            double totalAmount,
            double taxAmount,
            Appointment appointment
    ) {
        this.id = String.format("INV%03d", indexId++);
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.appointment = appointment;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getId() {
        return id;
    }

    public static void reset() {
        indexId = 1;
    }
}
