package com.tauseef.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Appointment {

    private static int nextId = 1;
    private String id;
    private Patient patient;
    private LocalDateTime dateTime;
    private ArrayList<Treatment> treatments;
    private Dermatologist dermatologist;

    public Appointment(Patient patient, Dermatologist dermatologist, LocalDateTime dateTime) {
        this.id = String.format("AID%03d", nextId++);
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

    public Treatment getTreatment(String name) {
        return treatments.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatment(Treatment treatment) {
        this.treatments.add(treatment);
    }

    public double getTreatmentTotal()
    {
        double total = 0;
        if (!this.getTreatments().isEmpty()) {
            for (Treatment treatment : this.getTreatments()) {
                total += treatment.getPrice();
            }
        }
        return total;
    }

    public double getTaxAmount()
    {
        double taxPercent = 15;
        double totalTreatment = getTreatmentTotal();

        return totalTreatment > 0 ? (totalTreatment * taxPercent) / 100 : 0.00;
    }

    public double getFullTotal()
    {
        return getTreatmentTotal() + getTaxAmount();
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

}
