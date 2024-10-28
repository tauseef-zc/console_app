package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.entities.Treatment;
import com.tauseef.app.entities.WorkDay;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface IClinicService extends ServiceInterface{

    ArrayList<LocalTime> getAvailableTimeSlots(
            LocalDate date,
            Dermatologist dermatologist
    );

    ArrayList<Treatment> getAllTreatments();

    private ArrayList<LocalTime> generateTimeSlots(
            WorkDay workDay,
            List<Appointment> filterAppointments)
    {
        return null;
    }

    private void loadBusinessDays(){};

    private void loadTreatments(){};

}
