package com.rhms;

import java.util.ArrayList;
import java.util.List;

public class MonitoringSystem {
    private String systemId;
    private List<Patient> registeredPatients;
    private List<Doctor> registeredDoctors;
    private List<Alert> systemAlerts;
    private boolean active;
    
    public MonitoringSystem(String systemId) {
        this.systemId = systemId;
        this.registeredPatients = new ArrayList<>();
        this.registeredDoctors = new ArrayList<>();
        this.systemAlerts = new ArrayList<>();
        this.active = true;
    }
    
    public void registerPatient(Patient patient) {
        if (!registeredPatients.contains(patient)) {
            registeredPatients.add(patient);
            System.out.println("Patient registered: " + patient.getFullName());
        }
    }
    
    public void registerDoctor(Doctor doctor) {
        if (!registeredDoctors.contains(doctor)) {
            registeredDoctors.add(doctor);
            System.out.println("Doctor registered: Dr. " + doctor.getLastName());
        }
    }
    
    public void processVitalSigns(Patient patient, VitalSigns vitals) {
        patient.recordVitalSigns(vitals);
        
        if (vitals.isAbnormal()) {
            Alert alert = new Alert(patient, vitals, "Abnormal readings detected");
            systemAlerts.add(alert);
            
            Doctor doctor = patient.getAssignedDoctor();
            if (doctor != null) {
                doctor.receiveAlert(alert);
            }
        }
        
        System.out.println("Processed vital signs for " + patient.getFullName());
    }
    
    public List<Alert> getUnreviewedAlerts() {
        return systemAlerts.stream()
                .filter(alert -> !alert.isReviewed())
                .toList();
    }
    
    public List<Patient> getPatientsWithAbnormalReadings() {
        List<Patient> abnormalPatients = new ArrayList<>();
        for (Patient patient : registeredPatients) {
            List<VitalSigns> history = patient.getVitalSignsHistory();
            if (!history.isEmpty() && history.get(history.size() - 1).isAbnormal()) {
                abnormalPatients.add(patient);
            }
        }
        return abnormalPatients;
    }
    
    public void generateReport() {
        System.out.println("\n=== Monitoring System Report ===");
        System.out.println("System ID: " + systemId);
        System.out.println("Registered Patients: " + registeredPatients.size());
        System.out.println("Registered Doctors: " + registeredDoctors.size());
        System.out.println("Total Alerts: " + systemAlerts.size());
        System.out.println("Unreviewed Alerts: " + getUnreviewedAlerts().size());
        
        System.out.println("\nPatients with recent abnormal readings:");
        for (Patient patient : getPatientsWithAbnormalReadings()) {
            System.out.println("- " + patient.getFullName());
        }
    }
    
    public String getSystemId() {
        return systemId;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
