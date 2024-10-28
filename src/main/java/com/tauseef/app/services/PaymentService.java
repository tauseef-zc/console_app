package com.tauseef.app.services;

import com.tauseef.app.entities.*;
import com.tauseef.app.enums.Color;
import com.tauseef.app.repositories.AppointmentRepository;
import com.tauseef.app.repositories.InvoiceRepository;
import com.tauseef.app.services.interfaces.IPaymentService;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentService extends BaseService implements IPaymentService {

    private final AppointmentRepository appointments;
    private final InvoiceRepository invoices;


    public PaymentService(AppointmentRepository appointments, InvoiceRepository invoices) {
        this.appointments = appointments;
        this.invoices = invoices;
    }

    public void generateInvoice() {
        console.title("Generate an Invoice");
        String appointmentId = console.ask("Please enter the Appointment ID to generate the invoice: ");

        if (appointmentId.isEmpty()) {
            console.error("You must enter the Appointment ID to generate the invoice");
            this.generateInvoice();
        }

        Appointment appointment = appointments.findById(appointmentId);

        if (appointment == null) {
            console.error("Appointment not found");
            this.handleContinueOption();

        } else {
            Invoice invoice = invoices.findByAppointment(appointment);
            if (invoice == null) {
                LocalDateTime dateTime = LocalDateTime.now();
                invoice = invoices.create(
                        new Invoice(dateTime, appointment.getFullTotal(), appointment.getTaxAmount(), appointment)
                );
            }

            this.generateInvoiceView(invoice);
            this.handleContinueOption();
        }

    }

    private void generateInvoiceView(Invoice invoice) {
        Appointment appointment = invoice.getAppointment();
        Patient patient = appointment.getPatient();
        Dermatologist doctor = appointment.getDermatologist();

        String title = "AURORA SKIN CARE CLINIC - INVOICE";
        String separator = "-".repeat(title.length() + 22);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        console.title(title);
        console.text("Invoice ID: #" + invoice.getId());
        console.text("Invoice Date: " + invoice.getInvoiceDate().format(formatter));
        console.text("Dermatologist: " + doctor.getName());
        console.text(separator);
        console.text(Color.WHITE_UNDERLINED + "Patient Information");
        console.text("Name: " + patient.getName());
        console.text("Email: " + patient.getEmail());
        console.text("Phone: " + patient.getPhone());
        console.text(separator);
        System.out.printf("%-44s %-12s%n", "Registration Fee: ", formatNumber(Appointment.registrationFee, 2));
        if (!appointment.getTreatments().isEmpty()) {
            console.text(Color.WHITE_UNDERLINED + "Treatments");
            for (Treatment treatment : appointment.getTreatments()) {
                System.out.printf("%-44s %-12s%n",
                        treatment.getName(),
                        formatNumber(treatment.getPrice(), 2));
            }
            console.text(separator);
        }
        System.out.printf("%-44s %-12s%n", "Total Treatment: ", formatNumber(appointment.getTreatmentTotal(), 2));
        System.out.printf("%-44s %-12s%n", "Tax (" + Appointment.taxPercentage + "%) Amount: ",
                formatNumber(invoice.getTaxAmount(), 2));
        System.out.printf("%-44s %-12s%n", "Total Amount: ", formatNumber(invoice.getTotalAmount(), 2));
        console.text(separator);
        console.emptySpace();
    }

    private void handleContinueOption() {
        String[] options = {"Generate another Invoice", "Back to Main Menu"};
        int option = console.askOption("Please enter a option: ",
                "Where do you need to navigate?",
                options);

        if (option == 1) {
            generateInvoice();
        }
    }

    private String formatNumber(double amount, int decimalPlaces) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(decimalPlaces);
        formatter.setMaximumFractionDigits(decimalPlaces);
        formatter.setGroupingUsed(true);
        return formatter.format(amount);
    }
}
