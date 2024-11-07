package com.tauseef.app.services;

import com.tauseef.app.TestCase;
import com.tauseef.app.entities.Appointment;
import com.tauseef.app.repositories.AppointmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest extends TestCase {

    @Test
    void makeAppointment() {
        String sb = faker.name().firstName() + "\n" +
                faker.internet().emailAddress() + "\n" +
                faker.phoneNumber().phoneNumber() + "\n" +
                faker.number().numberBetween(100000, 999999) + "\n" +
                faker.number().numberBetween(10, 100) + "\n" +
                1 + "\n" +
                "2024-10-16\n" +
                1 + "\n" +
                1 + "\n" +
                2 + "\n" +
                2 + "\n";
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, getScanner(sb));
        appointmentService.makeAppointment();

        assertFalse(appointmentRepository.getAppointments().isEmpty());
    }

    @Test
    void rescheduleAppointment() {

        String sb = """
                AID001
                1
                2024-10-18
                1
                """;
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, getScanner(sb));

        Appointment appointment = appointmentRepository.create(createAppointment());
        appointmentService.updateAppointment();
        Appointment updatedAppointment = appointmentRepository.findById(appointment.getId());

        assertEquals(updatedAppointment.getDate(), LocalDate.parse("2024-10-18"));
    }

    @Test
    void updateAppointmentInformation() {

        String sb = """
                AID001
                2
                2
                Ahamed
                2
                2
                """;
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, getScanner(sb));

        Appointment appointment = appointmentRepository.create(createAppointment());
        String name = appointment.getPatient().getName();

        appointmentService.updateAppointment();
        Appointment updatedAppointment = appointmentRepository.findById(appointment.getId());

        assertNotEquals(updatedAppointment.getPatient().getName(), name);
    }

    @Test
    void cancelAppointment() {

        String sb = """
                AID001
                3
                1
                """;
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, getScanner(sb));

        Appointment appointment = appointmentRepository.create(createAppointment());
        appointmentService.updateAppointment();
        Appointment updatedAppointment = appointmentRepository.findById(appointment.getId());

        assertNull(updatedAppointment);
    }

    @AfterEach
    void tearDown() {
        Appointment.reset();
    }
}