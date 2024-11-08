package com.tauseef.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Appointment {

    public final static double registrationFee = 500.00;
    public final static double taxPercentage = 2.5;
    public static int indexId = 1;
    private final String id;
    private final Patient patient;
    private LocalDateTime dateTime;
    private final ArrayList<Treatment> treatments;
    private final Dermatologist dermatologist;

    public Appointment(Patient patient, Dermatologist dermatologist, LocalDateTime dateTime) {
        this.id = String.format("AID%03d", indexId++);
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.dateTime = dateTime;
        this.treatments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatment(Treatment treatment) {
        this.treatments.add(treatment);
    }

    public double getTreatmentTotal() {
        double total = 0;
        if (!this.getTreatments().isEmpty()) {
            for (Treatment treatment : this.getTreatments()) {
                total += treatment.getPrice();
            }
        }
        return Math.round(registrationFee + total);
    }

    public double getTaxAmount() {
        double totalTreatment = getTreatmentTotal();
        double taxAmount = totalTreatment > 0 ? (totalTreatment * taxPercentage) / 100 : 0.00;
        return Math.round(taxAmount);
    }

    public double getFullTotal() {
        return Math.round(getTreatmentTotal() + getTaxAmount());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", patient=" + patient +
                ", dateTime=" + dateTime +
                ", treatment=" + (treatments != null ? treatments.toString() : "Not set") +
                '}';
    }

    public static void reset(){
        indexId = 1;
    }

}
