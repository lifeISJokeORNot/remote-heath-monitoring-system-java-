package com.rhms;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private String medicalRecordNumber;
    private List<String> chronicConditions;
    private Doctor assignedDoctor;
    private List<VitalSigns> vitalSignsHistory;
    private List<Alert> alerts;
    private WearableDevice assignedDevice;
    
    public Patient(String id, String firstName, String lastName, String email, String phone, 
                   String medicalRecordNumber) {
        super(id, firstName, lastName, email, phone);
        this.medicalRecordNumber = medicalRecordNumber;
        this.chronicConditions = new ArrayList<>();
        this.vitalSignsHistory = new ArrayList<>();
        this.alerts = new ArrayList<>();
    }
    
    public void addChronicCondition(String condition) {
        chronicConditions.add(condition);
    }
    
    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
        doctor.addPatient(this);
    }
    
    public void assignDevice(WearableDevice device) {
        this.assignedDevice = device;
        device.setPatient(this);
    }
    
    public void recordVitalSigns(VitalSigns vitalSigns) {
        vitalSignsHistory.add(vitalSigns);
        
        // Check for abnormalities
        if (vitalSigns.isAbnormal()) {
            Alert alert = new Alert(this, vitalSigns, "Abnormal vital signs detected");
            alerts.add(alert);
            
            if (assignedDoctor != null) {
                assignedDoctor.receiveAlert(alert);
            }
        }
    }
    
    public List<VitalSigns> getVitalSignsHistory() {
        return vitalSignsHistory;
    }
    
    public List<Alert> getAlerts() {
        return alerts;
    }
    
    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }
    
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }
    
    public List<String> getChronicConditions() {
        return chronicConditions;
    }
    
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", MRN='" + medicalRecordNumber + '\'' +
                '}';
    }
}
