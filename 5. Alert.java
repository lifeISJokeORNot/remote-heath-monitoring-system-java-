package com.rhms;

import java.time.LocalDateTime;

public class Alert {
    private String alertId;
    private Patient patient;
    private VitalSigns vitalSigns;
    private LocalDateTime timestamp;
    private String message;
    private boolean reviewed;
    private String recommendation;
    private Doctor reviewedBy;
    private static int alertCounter = 0;
    
    public Alert(Patient patient, VitalSigns vitalSigns, String message) {
        this.alertId = generateAlertId();
        this.patient = patient;
        this.vitalSigns = vitalSigns;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.reviewed = false;
    }
    
    private String generateAlertId() {
        alertCounter++;
        return "ALERT-" + System.currentTimeMillis() + "-" + alertCounter;
    }
    
    // Getters and Setters
    public String getAlertId() { return alertId; }
    
    public Patient getPatient() { return patient; }
    
    public VitalSigns getVitalSigns() { return vitalSigns; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    
    public String getMessage() { return message; }
    
    public boolean isReviewed() { return reviewed; }
    public void setReviewed(boolean reviewed) { this.reviewed = reviewed; }
    
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    
    public Doctor getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(Doctor reviewedBy) { this.reviewedBy = reviewedBy; }
    
    @Override
    public String toString() {
        return "Alert{" +
                "id='" + alertId + '\'' +
                ", patient=" + patient.getFullName() +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", reviewed=" + reviewed +
                '}';
    }
}
