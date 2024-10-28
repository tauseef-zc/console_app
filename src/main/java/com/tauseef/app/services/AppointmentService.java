package com.tauseef.app.services;

import com.tauseef.app.entities.Appointment;
import com.tauseef.app.entities.Dermatologist;
import com.tauseef.app.entities.Patient;
import com.tauseef.app.entities.Treatment;
import com.tauseef.app.repositories.AppointmentRepository;
import com.tauseef.app.services.interfaces.IAppointmentService;
import com.tauseef.app.services.interfaces.IClinicService;
import com.tauseef.app.services.interfaces.IDermatologistService;
import com.tauseef.app.services.interfaces.IPatientService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppointmentService extends BaseService implements IAppointmentService {

    private final IPatientService patientService;
    private final IDermatologistService dermatologistService;
    private final IClinicService clinicService;
    private final AppointmentRepository appointments;

    public AppointmentService(AppointmentRepository appointments) {
        super();
        this.appointments = appointments;
        this.patientService = new PatientService();
        this.dermatologistService = new DermatologistService();
        this.clinicService = new ClinicService(this.appointments);
    }

    public void makeAppointment() {
        console.title("Make an Appointment");

        Patient patient = patientService.createPatient();
        Dermatologist doctor = dermatologistService.selectDoctor();
        LocalDateTime appointmentDate = this.getAvailableSlot(doctor);

        boolean isPaid = console.askBoolean("We will charge registration fee Rs.500 for make the appointment? " +
                "Do you want to continue?");

        if (isPaid) {
            Appointment appointment = appointments.create(new Appointment(patient, doctor, appointmentDate));
            console.success("Appointment created successfully!");

            this.displayAppointment(appointment);

            if (console.askBoolean("Do you want to add another appointment?")) {
                this.makeAppointment();
            }

        } else {
            console.error("Appointment not created!");
        }
    }

    public void viewAppointments() {

        console.title("View Appointments by Date");

        LocalDate appointmentDate = console.askDate("Please enter appointment date (YYYY-MM-DD):");
        List<Appointment> filterAppointments = appointments.findByDate(appointmentDate);

        if (!filterAppointments.isEmpty()) {
            ArrayList<Dermatologist> doctors = dermatologistService.getDoctors();
            for (Dermatologist doctor : doctors) {
                console.success("Appointments of " + doctor.getName());
                List<Appointment> appointments = filterAppointments.stream()
                        .filter(appointment -> appointment.getDermatologist().equals(doctor))
                        .toList();
                generateTable(appointments);
            }

            if (console.askBoolean("Do you want to view an individual appointment?")) {
                displayAppointmentConfirmation(filterAppointments);
            } else {
                handleContinueOption(filterAppointments);
            }

        } else {
            console.emptySpace();
            console.error("No appointments found!");
            console.emptySpace();
        }
    }

    public void searchAppointments()
    {
        console.title("Search Appointment");
        String[] options = {"By Appointment ID", "By Patient name or NIC"};

        int searchOption = console.askOption(
                "Please select a option:",
                "Select your search type",
                options
                );

        if (searchOption == 1) {
            displayAppointmentConfirmation(appointments.getAppointments());
        } else {
            String answer = console.ask("Please enter Patient name or NIC: ");
            List<Appointment> filterAppointments = appointments.findByPatientNameOrNic(answer);
            if (!filterAppointments.isEmpty()) {
                generateTable(filterAppointments);
                displayAppointmentConfirmation(filterAppointments);
            } else {
                console.error("No appointments found!");
            }
        }
    }

    public void updateAppointment()
    {
        console.title("Update an Appointment");
        String appointmentId = console.ask("Please enter an appointment ID: ");

        Appointment appointment = getAppointment(appointmentId);

        if (appointment != null) {
            String[] options = {"Reschedule the Appointment", "Update Information", "Cancel Appointment"};
            int option = console.askOption(
                    "Please enter an option: ",
                    "Choose the update option for this Appointment?",
                    options);
            updateAppointment(appointment, option);
        } else {
            console.error("Appointment not found!");
            if (console.askBoolean("Do you want to try with another Appointment ID?")) {
                updateAppointment();
            }
        }
    }

    private LocalDateTime getAvailableSlot(Dermatologist doctor)
    {
        while (true) {

            LocalDate appointmentDate = console.askDate("Please enter appointment date (YYYY-MM-DD):");
            ArrayList<LocalTime> slots = clinicService.getAvailableTimeSlots(appointmentDate, doctor);

            if (!slots.isEmpty()) {
                int i = 0;
                String[] availableSlots = new String[slots.size()];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                for (LocalTime slot : slots) {
                    availableSlots[i++] = slot.format(formatter);
                }
                int selectedIndex = console.askOption("Please select your convenient time:", "Available Times", availableSlots) - 1;
                return appointmentDate.atTime(slots.get(selectedIndex));
            }
        }
    }

    private void displayAppointmentConfirmation(List<Appointment> appointments)
    {
        String appointmentId = console.ask("Please enter an appointment ID: ");
        Appointment appointment = getAppointment(appointmentId);

        if (appointment != null) {
            displayAppointment(appointment);
        } else {
            console.error("Appointment not found! Please check the appointment ID.");
            handleContinueOption(appointments);
        }

    }

    private void handleContinueOption(List<Appointment> filterAppointments)
    {
        String[] options = {"View Appointments", "Back to Main Menu"};
        int option = console.askOption("Please enter a option: ", "Do you want to continue?", options);
        if (option == 1) {
            displayAppointmentConfirmation(filterAppointments);
        }
    }

    private void displayAppointment(Appointment appointment)
    {
        generateAppointmentView(appointment);
        if (console.askBoolean("Do you want to start a treatment?")) {
            addTreatmentsToAppointment(appointment);
        }
    }

    private void addTreatmentsToAppointment(Appointment appointment)
    {
        ArrayList<Treatment> treatments = clinicService.getAllTreatments();
        String[] treatmentNames = new String[treatments.size()];

        int index = 0;
        for (Treatment treatment : treatments) {
            treatmentNames[index++] = treatment.getName() + " - Rs." + treatment.getPrice();
        }
        int option = console.askOption("Please select a treatment: ", "Available Treatments", treatmentNames);

        Treatment treatment = treatments.get(option - 1);
        appointment.setTreatment(treatment);

        console.success("Treatment added to appointment! \n");

        if (console.askBoolean("Do you want to add more treatments?")) {
            addTreatmentsToAppointment(appointment);
        }
    }

    private void updateAppointment(Appointment appointment, int updateType)
    {
        switch (updateType) {
            case 1:
                console.text("Your current appointment at " + appointment.getDateTime().toString());
                LocalDateTime rescheduleDate = this.getAvailableSlot(appointment.getDermatologist());
                rescheduleAppointment(appointment, rescheduleDate);
                break;
            case 2:
                updateInformation(appointment);
                break;
            case 3:
                cancelAppointment(appointment);
                break;
        }
    }

    private void cancelAppointment(Appointment appointment) {
        if (console.askBoolean("Do you want to cancel this appointment?")) {
            appointments.delete(appointment);
            console.success("The Appointment is cancelled!");
        }
    }

    private void rescheduleAppointment(Appointment appointment, LocalDateTime date) {
        appointment.setDateTime(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        console.success("Your appointment is rescheduled to " + appointment.getDateTime().format(formatter));
        console.success("Appointment updated successfully!");
    }

    private void updateInformation(Appointment appointment)
    {
        HashMap<String, String> fieldList = appointment.getPatient().fieldSet();
        String[] options = fieldList.values().toArray(new String[0]);

        int fieldId = console.askOption("Please select a field: ", "Patient Information Fields", options) - 1;

        String option = options[fieldId];
        String fieldName = fieldList.keySet().toArray()[fieldId].toString();

        String question = console.ask("Please enter the " + option + " to update: ");
        boolean isUpdated = appointment.getPatient().updateField(fieldName,
                question,
                console);

        if (isUpdated && console.askBoolean("Do you need to view the updated Appointment?")) {
            generateAppointmentView(appointment);
        }

        if (console.askBoolean("Do you need to update any other field?")) {
            updateInformation(appointment);
        }

    }

    private void generateAppointmentView(Appointment appointment) {

        StringBuilder builder = new StringBuilder();
        Patient patient = appointment.getPatient();
        Dermatologist doctor = appointment.getDermatologist();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

        console.emptySpace();
        console.title("Appointment details");

        builder.append("- Appointment ID: ").append(appointment.getId()).append("\n");
        builder.append("- Appointment Date: ").append(appointment.getDateTime().format(formatter)).append("\n");
        builder.append("- Dermatologist: ").append(doctor.getName()).append("\n");
        builder.append("- Patient Name: ").append(patient.getName()).append("\n");
        builder.append("- Patient Email: ").append(patient.getEmail()).append("\n");
        builder.append("- Patient Phone: ").append(patient.getPhone()).append("\n");
        builder.append("- Patient NIC: ").append(patient.getNic()).append("\n");
        builder.append("- Patient Age: ").append(patient.getAge());
        console.text(builder.toString());

        if (!appointment.getTreatments().isEmpty()) {
            console.title("Treatments");
            for (Treatment treatment : appointment.getTreatments()) {
                console.text("- " + treatment.getName());
            }
        }

        console.emptySpace();
    }

    private void generateTable(List<Appointment> appointments)
    {
        console.text("-".repeat(82));
        System.out.printf("%-6s | %-16s | %-20s | %-12s | %-16s%n",
                "ID",
                "Appointment Time",
                "Patient Name",
                "Phone Number",
                "NIC");
        console.text("-".repeat(82));
        appointments.forEach(appointment -> System.out.printf("%-6s | %-16s | %-20s | %-12s | %-16s%n",
                appointment.getId(),
                appointment.getDateTime().toLocalTime().toString(),
                appointment.getPatient().getName(),
                appointment.getPatient().getPhone(),
                appointment.getPatient().getNic()
        ));
        console.emptySpace();
    }

    public Appointment getAppointment(String appointmentId) {
        return appointments.findById(appointmentId);
    }
}
