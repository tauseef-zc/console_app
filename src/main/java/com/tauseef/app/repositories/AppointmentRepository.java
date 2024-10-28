package com.tauseef.app.repositories;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Dermatologist;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private final ArrayList<Appointment> appointments;

    public AppointmentRepository() {
        this.appointments = new ArrayList<>();
    }

    public Appointment create(Appointment appointment) {
        appointments.add(appointment);
        return appointment;
    }

    public Appointment findById(String id) {
        return appointments.stream()
                .filter(appointment -> appointment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> findByDate(LocalDate date) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().toString().equals(date.toString()))
                .toList();
    }

    public List<Appointment> findByDateAndDermatologist(LocalDate date, Dermatologist dermatologist) {
        return appointments.stream()
                .filter(appointment -> appointment.getDate().equals(date)
                        && appointment.getDermatologist().getId() == dermatologist.getId())
                .toList();
    }

    public List<Appointment> findByPatientNameOrNic(String searchTerm) {
        return appointments.stream()
                .filter(appointment ->
                        appointment.getPatient().getName().equals(searchTerm) ||
                                appointment.getPatient().getNic().equals(searchTerm))
                .toList();
    }

    public void delete(Appointment appointment) {
        appointments.remove(appointment);
    }

}
