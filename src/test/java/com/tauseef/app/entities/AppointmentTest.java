package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest extends TestCase {

    @Test
    void getPatient() {
        Patient patient = createPatient();
        Appointment appointment = createAppointment(patient, null, null);

        assertEquals(patient, appointment.getPatient());
    }

    @Test
    void getDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        Appointment appointment = createAppointment(null, null, dateTime);

        assertEquals(dateTime, appointment.getDateTime());
    }

    @Test
    void getDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        Appointment appointment = createAppointment(null, null, dateTime);

        assertEquals(dateTime.toLocalDate(), appointment.getDate());
    }

    @Test
    void setDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        Appointment appointment = createAppointment();
        appointment.setDateTime(dateTime);

        assertEquals(dateTime, appointment.getDateTime());
    }

    @Test
    void getDermatologist() {
        Dermatologist dermatologist = createDoctor();
        Appointment appointment = createAppointment(null, dermatologist, null);

        assertEquals(dermatologist, appointment.getDermatologist());
    }

    @Test
    void getTreatments() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);

        assertEquals(treatment, appointment.getTreatments().stream().findFirst().get());
    }

    @Test
    void setTreatment() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);

        assertEquals(treatment, appointment.getTreatments().stream().findFirst().get());
    }

    @Test
    void getTreatmentTotal() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double expectedTotal = Appointment.registrationFee + treatment.getPrice();

        assertEquals(expectedTotal, appointment.getTreatmentTotal());
    }

    @Test
    void getTaxAmount() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double totalAmount = Appointment.registrationFee + treatment.getPrice();
        double expectedTax = totalAmount * Appointment.taxPercentage / 100;

        assertEquals(expectedTax, appointment.getTaxAmount());
    }

    @Test
    void getFullTotal() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double totalAmount = Appointment.registrationFee + treatment.getPrice();
        double taxAmount = totalAmount * Appointment.taxPercentage / 100;
        double expectedFullTotal = totalAmount + taxAmount;

        assertEquals(expectedFullTotal, appointment.getFullTotal());
    }
}