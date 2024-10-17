package com.tauseef.app.services;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.entities.Treatment;
import com.tauseef.app.entities.WorkDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClinicService extends BaseService{

    private final ArrayList<WorkDay> workingDays;
    private final ArrayList<Treatment> treatments;

    public ClinicService() {

        workingDays = new ArrayList<WorkDay>();
        treatments = new ArrayList<Treatment>();

        loadBusinessDays();
        loadTreatments();
    }

    public ArrayList<LocalTime> getAvailableTimeSlots(
            LocalDate date,
            Dermatologist dermatologist,
            ArrayList<Appointment> appointments
    ) {

        DayOfWeek day = date.getDayOfWeek();
        Optional<WorkDay> appointmentDay = workingDays.stream().filter(workDay->workDay.getDay().equals(day)).findAny();

        if(appointmentDay.isEmpty()) {
            console.error("Appointment not available on selected day!");
            return new ArrayList<LocalTime>();
        }

        List<Appointment> filterAppointments = new ArrayList<>();

        if(!appointments.isEmpty()){
            filterAppointments = appointments.stream()
                    .filter(appointment -> appointment.getDate().equals(date)
                            && appointment.getDermatologist().getId() == dermatologist.getId())
                    .toList();
        }

        return generateTimeSlots(appointmentDay.get(), filterAppointments);
    }

    private ArrayList<LocalTime> generateTimeSlots(WorkDay workDay, List<Appointment> filterAppointments)
    {
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

        return availableSlots;
    }

    public ArrayList<Treatment> getAllTreatments() {
        return treatments;
    }

    private void loadBusinessDays()
    {
        WorkDay monday = new WorkDay(DayOfWeek.MONDAY, LocalTime.of(10, 00), LocalTime.of(13, 00));
        WorkDay wednesday = new WorkDay(DayOfWeek.WEDNESDAY, LocalTime.of(14, 00), LocalTime.of(17, 00));
        WorkDay friday = new WorkDay(DayOfWeek.FRIDAY, LocalTime.of(14, 00), LocalTime.of(17, 00));
        WorkDay saturday = new WorkDay(DayOfWeek.SATURDAY, LocalTime.of(14, 00), LocalTime.of(17, 00));

        workingDays.add(monday);
        workingDays.add(wednesday);
        workingDays.add(friday);
        workingDays.add(saturday);
    }

    private void loadTreatments()
    {
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
