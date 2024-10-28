package com.tauseef.app;

import com.github.javafaker.Faker;
import com.tauseef.app.entities.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestCase {

    public final Faker faker = new Faker();

    public Treatment createTreatment() {
        return createTreatment(null, 0.00);
    }

    public Treatment createTreatment(String name, double price) {
        String treatment = name != null ? name : faker.commerce().department();
        double treatmentPrice = price > 0 ? price : faker.number().randomDouble(2, 1000, 5000);

        return new Treatment(treatment, treatmentPrice);
    }

    public Dermatologist createDoctor() {
        return createDoctor(null);
    }

    public Dermatologist createDoctor(String name) {
        String doctorName = name != null ? name : faker.name().firstName();
        return new Dermatologist(doctorName);
    }

    public Patient createPatient() {
        return createPatient(null, null, null, null, 0);
    }

    public Patient createPatient(String name, String address, String phone, String nic, int age) {

        String patientName = name != null ? name : faker.name().firstName();
        String patientEmail = address != null ? address : faker.internet().emailAddress();
        String patientPhone = phone != null ? phone : faker.phoneNumber().phoneNumber();
        String patientNic = nic != null ? nic : faker.number().numberBetween(1000000, 9999999) + "V";
        int patientAge = age > 0 ? age : faker.number().numberBetween(1, 100);

        return new Patient(
                patientName,
                patientEmail,
                patientPhone,
                patientNic,
                patientAge
        );
    }

    public Appointment createAppointment() {
        return createAppointment(null, null, null);
    }

    public Appointment createAppointment(Patient patient, Dermatologist dermatologist, LocalDateTime dateTime) {
        Patient fakePatient = patient != null ? patient : createPatient();
        Dermatologist fakeDoctor = dermatologist != null ? dermatologist : createDoctor();
        LocalDateTime fakeDateTime = dateTime != null ? dateTime : LocalDateTime.now();

        return new Appointment(
                fakePatient,
                fakeDoctor,
                fakeDateTime
        );
    }

    public Invoice createInvoice() {
        return createInvoice(null, null);
    }

    public Invoice createInvoice(LocalDateTime dateTime, Appointment appointment) {
        LocalDateTime invoiceDate = dateTime != null ? dateTime : LocalDateTime.now();
        Appointment fakeAppointment = appointment != null ? appointment : createAppointment();

        if (fakeAppointment.getTreatments().isEmpty()) {
            Treatment treatment = createTreatment();
            fakeAppointment.setTreatment(treatment);
        }

        return new Invoice(
                invoiceDate,
                fakeAppointment.getFullTotal(),
                fakeAppointment.getTaxAmount(),
                fakeAppointment
        );
    }

    public WorkDay createWorkDay(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        DayOfWeek wkDay = day != null ? day : DayOfWeek.WEDNESDAY;
        LocalTime wkStartTime = startTime != null ? startTime : LocalTime.now();
        LocalTime wkEndTime = endTime != null ? endTime : LocalTime.now();
        return new WorkDay(wkDay, wkStartTime, wkEndTime);
    }

}
