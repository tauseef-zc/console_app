package com.tauseef.app.services.interfaces;

import com.tauseef.app.entities.Dermatologist;

import java.util.ArrayList;

public interface IDermatologistService extends ServiceInterface{

    ArrayList<Dermatologist> getDoctors();

    Dermatologist selectDoctor();

}
