package com.tauseef.app.repositories;

import com.tauseef.app.TestCase;
import com.tauseef.app.entities.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentRepositoryTest extends TestCase {
    AppointmentRepository repo;

    @BeforeEach
    void setUp() {
        repo = new AppointmentRepository();
    }

    @Test
    void create() {
        Appointment appointment = createAppointment();
        repo.create(appointment);

        assertNotNull(repo.getAppointments());
    }

    @Test
    void findById() {
        Appointment appointment = createAppointment();
        repo.create(appointment);

        Appointment found = repo.findById(appointment.getId());

        assertNotNull(found);
        assertEquals(appointment.getId(), found.getId());

    }

    @Test
    void getAppointments() {
        Appointment appointment = createAppointment();
        repo.create(appointment);
        assertNotNull(repo.getAppointments());
    }

    @Test
    void findByDate() {
        Appointment appointment = createAppointment();
        repo.create(appointment);
        List<Appointment> found = repo.findByDate(appointment.getDate());

        assertNotNull(found);
        assertEquals(appointment.getDate(), found.getFirst().getDate());
    }

    @Test
    void findByDateAndDermatologist() {
        Appointment appointment = createAppointment();
        repo.create(appointment);
        List<Appointment> found = repo.findByDateAndDermatologist(appointment.getDate(), appointment.getDermatologist());
        assertNotNull(found);
        assertTrue(found.stream().
                anyMatch(a -> a.getDate().equals(appointment.getDate()) &&
                        a.getDermatologist().equals(appointment.getDermatologist())));
    }

    @Test
    void findByPatientNameOrNic() {
        Appointment appointment = createAppointment();
        repo.create(appointment);
        List<Appointment> found = repo.findByPatientNameOrNic(appointment.getPatient().getName());

        assertNotNull(found);
        assertEquals(appointment.getPatient().getName(), found.getFirst().getPatient().getName());
    }

    @Test
    void delete() {
        Appointment appointment = createAppointment();

        repo.create(appointment);
        Appointment found = repo.findById(appointment.getId());
        assertNotNull(found);

        repo.delete(found);
        assertNull(repo.findById(found.getId()));
    }
}