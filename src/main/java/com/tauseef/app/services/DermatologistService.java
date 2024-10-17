package com.tauseef.app.services;

import com.tauseef.app.entities.Dermatologist;
import java.util.ArrayList;

public class DermatologistService extends BaseService{

    private final ArrayList<Dermatologist> doctors;

    public DermatologistService() {
        super();
        this.doctors = new ArrayList<Dermatologist>();
        seedData();
    }

    public void seedData() {
        Dermatologist doctor1 = new Dermatologist(1, "Dr.Perera");
        Dermatologist doctor2 = new Dermatologist(2, "Dr.Herath");
        doctors.add(doctor1);
        doctors.add(doctor2);
    }

    public ArrayList<Dermatologist> getDoctors() {
        return doctors;
    }

    public Dermatologist selectDoctor() {
        String[] doctorNames = getDoctorNames();
        int doctorId = console.askOption(
                "Please select the dermatologist you wish to see: ",
                "Dermatologists",
                doctorNames);
        return doctors.get(doctorId - 1);
    }

    private String[] getDoctorNames() {
        String[] doctorNames = new String[doctors.size()];
        for (Dermatologist doctor : doctors) {
            doctorNames[doctor.getId() - 1] = doctor.getName();
        }
        return doctorNames;
    }
}
