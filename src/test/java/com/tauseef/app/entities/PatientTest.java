package com.tauseef.app.entities;

import com.tauseef.app.TestCase;
import com.tauseef.app.core.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest extends TestCase {

    @BeforeEach
    void setUp() {
        Patient.reset();
    }

    @Test
    void getId() {
        Patient patient = createPatient();
        assertEquals(1, patient.getId());
    }

    @Test
    void getName() {
        String patientName = faker.name().firstName();
        Patient patient = createPatient(patientName, null, null, null, 0);

        assertEquals(patientName, patient.getName());
    }

    @Test
    void getEmail() {
        String emailAddress = faker.internet().emailAddress();
        Patient patient = createPatient(null, emailAddress, null, null, 0);

        assertEquals(emailAddress, patient.getEmail());
    }

    @Test
    void getPhone() {
        String phoneNumber = faker.phoneNumber().phoneNumber();
        Patient patient = createPatient(null, null, phoneNumber, null, 0);

        assertEquals(phoneNumber, patient.getPhone());
    }

    @Test
    void getNic() {
        String nic = faker.number().numberBetween(100000, 999999) + "V";
        Patient patient = createPatient(null, null, null, nic, 0);

        assertEquals(nic, patient.getNic());
    }

    @Test
    void getAge() {
        int age = faker.number().numberBetween(10, 100);
        Patient patient = createPatient(null, null, null, null, age);

        assertEquals(age, patient.getAge());
    }

    @Test
    void updateField() {
        Patient patient = createPatient();
        String name = faker.name().firstName();
        patient.updateField("name", name,  new Console());

        assertEquals(name, patient.getName());
    }

    @Test
    void testToString() {
        Patient patient = createPatient();
        String text = "Patient{" +
                "id='" + patient.getId() +
                ", name=" + patient.getName() +
                ", email=" + patient.getEmail() +
                ", phone=" + patient.getPhone() +
                ", nic=" + patient.getNic() +
                ", age=" + patient.getAge() +
                '}';
        assertEquals(text, patient.toString());
    }

    @Test
    void fieldSet() {
        Patient patient = createPatient();
        patient.fieldSet();
    }
}