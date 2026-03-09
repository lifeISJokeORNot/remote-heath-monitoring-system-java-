package com.rhms;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String licenseNumber;
    private String specialization;
    private List<Patient> patients;
    private List<Alert> pendingAlerts;
    
    public Doctor(String id, String firstName, String lastName, String email, String phone,
                  String licenseNumber, String specialization) {
        super(id, firstName, lastName, email, phone);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
        this.patients = new ArrayList<>();
        this.pendingAlerts = new ArrayList<>();
    }
    
    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }
    
    public void receiveAlert(Alert alert) {
        pendingAlerts.add(alert);
        System.out.println("Alert sent to Dr. " + getLastName() + ": " + alert.getMessage());
    }
    
    public void reviewAlert(Alert alert, String recommendation) {
        if (pendingAlerts.remove(alert)) {
            alert.setReviewed(true);
            alert.setRecommendation(recommendation);
            alert.setReviewedBy(this);
            
            System.out.println("Dr. " + getLastName() + " reviewed alert for patient " + 
                             alert.getPatient().getFullName());
            System.out.println("Recommendation: " + recommendation);
        }
    }
    
    public void scheduleFollowUp(Patient patient, String dateTime) {
        System.out.println("Follow-up scheduled for " + patient.getFullName() + 
                         " on " + dateTime + " with Dr. " + getLastName());
    }
    
    public List<Alert> getPendingAlerts() {
        return pendingAlerts;
    }
    
    public List<Patient> getPatients() {
        return patients;
    }
    
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
