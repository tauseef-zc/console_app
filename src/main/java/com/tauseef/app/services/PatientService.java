package com.tauseef.app.services;

import com.tauseef.app.entities.Patient;
import java.util.ArrayList;

public class PatientService extends BaseService{

    private final ArrayList<Patient> patients;

    public PatientService() {
        super();
        this.patients = new ArrayList<>();
    }

    public Patient createPatient() {

        String patientName  = console.ask("Enter patient name: ", true);
        String patientEmail = console.ask("Enter patient email: ");
        String patientPhone = console.ask("Enter patient phone: ", true);
        String patientNic   = console.ask("Enter patient NIC: ", true);
        int patientAge      = console.askNumber("Enter patient age: ");

        Patient patient = new Patient(patientName, patientEmail, patientPhone, patientNic, patientAge);
        patients.add(patient);

        return patient;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

}
