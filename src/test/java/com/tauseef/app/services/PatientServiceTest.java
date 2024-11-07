package com.tauseef.app.services;

import com.tauseef.app.TestCase;
import com.tauseef.app.entities.Patient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceTest extends TestCase {

    @Test
    void createPatientTest() {

        String sb = faker.name().firstName() + "\n" +
                faker.internet().emailAddress() + "\n" +
                faker.phoneNumber().phoneNumber() + "\n" +
                faker.number().numberBetween(100000, 999999) + "\n" +
                faker.number().numberBetween(10, 100);

        PatientService patientService = new PatientService(getScanner(sb));
        Patient patient = patientService.createPatient();

        assertNotNull(patient);
    }
}