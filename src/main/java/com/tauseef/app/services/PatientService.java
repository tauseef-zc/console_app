package com.tauseef.app.services;

import com.tauseef.app.entities.Patient;
import com.tauseef.app.services.interfaces.IPatientService;

import java.util.ArrayList;

public class PatientService extends BaseService implements IPatientService
{

    public PatientService() {
        super();
    }

    public Patient createPatient() {

        String patientName  = console.ask("Enter patient name: ", true);
        String patientEmail = console.ask("Enter patient email: ");
        String patientPhone = console.ask("Enter patient phone: ", true);
        String patientNic   = console.ask("Enter patient NIC: ", true);
        int patientAge      = console.askNumber("Enter patient age: ");

        return new Patient(patientName, patientEmail, patientPhone, patientNic, patientAge);
    }

}
