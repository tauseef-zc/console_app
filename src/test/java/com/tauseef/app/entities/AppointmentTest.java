package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(treatment, appointment.getTreatments().stream().findFirst().orElse(null));
    }

    @Test
    void setTreatment() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);

        assertEquals(treatment, appointment.getTreatments().stream().findFirst().orElse(null));
    }

    @Test
    void getTreatmentTotal() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double expectedTotal = Math.round(Appointment.registrationFee + treatment.getPrice());

        assertEquals(expectedTotal, appointment.getTreatmentTotal());
    }

    @Test
    void getTaxAmount() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double totalAmount = Math.round(Appointment.registrationFee + treatment.getPrice());
        double expectedTax = Math.round(totalAmount * Appointment.taxPercentage / 100);

        assertEquals(expectedTax, appointment.getTaxAmount());
    }

    @Test
    void getFullTotal() {
        Treatment treatment = createTreatment();
        Appointment appointment = createAppointment();
        appointment.setTreatment(treatment);
        double totalAmount = Math.round(Appointment.registrationFee + treatment.getPrice());
        double taxAmount = Math.round(totalAmount * Appointment.taxPercentage / 100);
        double expectedFullTotal = Math.round(totalAmount + taxAmount);

        assertEquals(expectedFullTotal, appointment.getFullTotal());
    }
}