package com.tauseef.app.services;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.entities.Treatment;
import com.tauseef.app.entities.WorkDay;
import com.tauseef.app.repositories.AppointmentRepository;
import com.tauseef.app.services.interfaces.IClinicService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicService extends BaseService implements IClinicService {

    private final AppointmentRepository appointments;
    private final ArrayList<WorkDay> workingDays;
    private final ArrayList<Treatment> treatments;

    public ClinicService(AppointmentRepository appointments) {
        this.appointments = appointments;
        workingDays = new ArrayList<>();
        treatments = new ArrayList<>();

        loadBusinessDays();
        loadTreatments();
    }

    public ArrayList<LocalTime> getAvailableTimeSlots(
            LocalDate date,
            Dermatologist dermatologist
    ) {

        DayOfWeek day = date.getDayOfWeek();
        WorkDay appointmentDay = workingDays.stream().filter(workDay -> workDay.getDay().equals(day))
                .findFirst()
                .orElse(null);

        if (appointmentDay == null) {
            console.error("Clinic is closed on given date. Please choose another date!");
            return new ArrayList<>();
        }

        List<Appointment> filterAppointments = new ArrayList<>();

        if (!appointments.getAppointments().isEmpty()) {
            filterAppointments = appointments.findByDateAndDermatologist(date, dermatologist);
        }

        return generateTimeSlots(appointmentDay, filterAppointments);
    }

    private ArrayList<LocalTime> generateTimeSlots(WorkDay workDay, List<Appointment> filterAppointments) {
        ArrayList<LocalTime> availableSlots = new ArrayList<>();
        LocalTime startTime = workDay.getStartTime();
        LocalTime endTime = workDay.getEndTime();

        while (startTime.isBefore(endTime)) {
            if (filterAppointments.isEmpty()) {
                availableSlots.add(startTime);
            } else {
                LocalTime finalStartTime = startTime;
                boolean usedSlot = filterAppointments.stream()
                        .anyMatch(appointment -> appointment.getDateTime().toLocalTime().equals(finalStartTime));
                if (!usedSlot) {
                    availableSlots.add(finalStartTime);
                }
            }
            startTime = startTime.plusMinutes(15);
        }

        if (availableSlots.isEmpty()) {
            console.error("Booking is closed on given date. Please choose another date!");
        }

        return availableSlots;
    }

    public ArrayList<Treatment> getAllTreatments() {
        return treatments;
    }

    private void loadBusinessDays() {
        WorkDay monday = new WorkDay(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(13, 0));
        WorkDay wednesday = new WorkDay(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));
        WorkDay friday = new WorkDay(DayOfWeek.FRIDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));
        WorkDay saturday = new WorkDay(DayOfWeek.SATURDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));

        workingDays.add(monday);
        workingDays.add(wednesday);
        workingDays.add(friday);
        workingDays.add(saturday);
    }

    private void loadTreatments() {
        Treatment acne = new Treatment("Acne Treatment", 2750.00);
        Treatment skin = new Treatment("Skin Whitening", 7650.00);
        Treatment mole = new Treatment("Mole Removal", 3850.00);
        Treatment laser = new Treatment("Laser Treatment", 12500.00);

        treatments.add(acne);
        treatments.add(skin);
        treatments.add(mole);
        treatments.add(laser);
    }
}
