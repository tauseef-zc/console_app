package com.tauseef.app.services;

import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.services.interfaces.IDermatologistService;

import java.util.ArrayList;
import java.util.Scanner;

public class DermatologistService extends BaseService implements IDermatologistService {

    private final ArrayList<Dermatologist> doctors;

    public DermatologistService() {
        super();
        this.doctors = new ArrayList<>();
        seedData();
    }

    public DermatologistService(Scanner scanner) {
        super(scanner);
        this.doctors = new ArrayList<>();
        seedData();
    }

    public void seedData() {
        doctors.add(new Dermatologist("Dr.Perera"));
        doctors.add(new Dermatologist("Dr.Herath"));
    }

    public ArrayList<Dermatologist> getDoctors() {
        return doctors;
    }

    public Dermatologist selectDoctor() {
        String[] doctorNames = getDoctorNames();
        int doctorId = console.askOption(
                "Please select the dermatologist you wish to see: ",
                "Dermatologists",
                doctorNames) - 1;
        return doctors.get(doctorId);
    }

    private String[] getDoctorNames() {
        String[] doctorNames = new String[doctors.size()];
        int i = 0;
        for (Dermatologist doctor : doctors) {
            doctorNames[i++] = doctor.getName();
        }
        return doctorNames;
    }
}
