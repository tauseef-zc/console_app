package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.entities.Treatment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface IClinicService extends ServiceInterface {

    ArrayList<LocalTime> getAvailableTimeSlots(
            LocalDate date,
            Dermatologist dermatologist
    );

    ArrayList<Treatment> getAllTreatments();

}
