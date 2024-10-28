package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Dermatologist;

import java.util.ArrayList;

public interface IDermatologistService
{
    void seedData();

    ArrayList<Dermatologist> getDoctors();

    Dermatologist selectDoctor();

    private String[] getDoctorNames() { return null; };
}
