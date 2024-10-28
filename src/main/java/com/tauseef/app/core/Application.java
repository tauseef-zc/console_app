package com.tauseef.app.core;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.repositories.AppointmentRepository;
import com.tauseef.app.repositories.InvoiceRepository;
import com.tauseef.app.services.*;
import com.tauseef.app.services.interfaces.*;

public class Application {

    private final IPaymentService paymentService;
    private final IAppointmentService appointmentService;
    private final Console console;
    private final String[] menuOptions = {
            "Make an appointment",
            "View Appointments",
            "Search Appointments",
            "Update an Appointment",
            "Generate an Invoice",
            "Exit System"
    };

    public Application(Console console) {

        AppointmentRepository appointmentRepository = new AppointmentRepository();
        InvoiceRepository invoiceRepository = new InvoiceRepository();
        this.appointmentService = new AppointmentService(appointmentRepository);
        this.paymentService = new PaymentService(appointmentRepository, invoiceRepository);
        this.console = console;
    }

    public void run() {
        while (true) {
            this.viewInfo();
            int option = console.askOption("Enter your choice: ", "Main Menu", menuOptions);

            switch (option) {
                case 1: appointmentService.makeAppointment(); break;
                case 2: appointmentService.viewAppointments(); break;
                case 3: appointmentService.searchAppointments(); break;
                case 4: appointmentService.updateAppointment(); break;
                case 5: paymentService.generateInvoice(); break;
                case 6: exitSystem();
                default: return;
            }
        }
    }

    private void exitSystem ()
    {
        console.success("Thank you for using the Aurora Skin Care System!");
        System.exit(0);
    }

    private void viewInfo(){
        console.heading("AURORA SKIN CARE CLINIC");
        console.highlight("Appointment Management System - V1.0");
        console.emptySpace();
    }

}
