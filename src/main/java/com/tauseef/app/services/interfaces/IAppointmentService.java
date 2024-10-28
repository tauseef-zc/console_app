package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Appointment;

import java.util.ArrayList;

public interface IAppointmentService extends ServiceInterface{

    void makeAppointment();

    void viewAppointments();

    void searchAppointments();

    void updateAppointment();

    Appointment getAppointment(String appointmentId);
}
