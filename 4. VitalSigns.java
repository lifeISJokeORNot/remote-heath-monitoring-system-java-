package com.rhms;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VitalSigns {
    private LocalDateTime timestamp;
    private double heartRate;
    private int systolicBP;
    private int diastolicBP;
    private double temperature;
    private double bloodGlucose;
    private double oxygenSaturation;
    private Patient patient;
    private Map<String, Boolean> abnormalFlags;
    
    // Normal ranges
    private static final double NORMAL_HEART_RATE_MIN = 60;
    private static final double NORMAL_HEART_RATE_MAX = 100;
    private static final int NORMAL_SYSTOLIC_MIN = 90;
    private static final int NORMAL_SYSTOLIC_MAX = 120;
    private static final int NORMAL_DIASTOLIC_MIN = 60;
    private static final int NORMAL_DIASTOLIC_MAX = 80;
    private static final double NORMAL_TEMP_MIN = 36.5;
    private static final double NORMAL_TEMP_MAX = 37.5;
    private static final double NORMAL_BLOOD_GLUCOSE_MIN = 70;
    private static final double NORMAL_BLOOD_GLUCOSE_MAX = 140;
    private static final double NORMAL_O2_MIN = 95;
    private static final double NORMAL_O2_MAX = 100;
    
    public VitalSigns(Patient patient) {
        this.patient = patient;
        this.timestamp = LocalDateTime.now();
        this.abnormalFlags = new HashMap<>();
    }
    
    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
        checkHeartRate();
    }
    
    public void setBloodPressure(int systolic, int diastolic) {
        this.systolicBP = systolic;
        this.diastolicBP = diastolic;
        checkBloodPressure();
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        checkTemperature();
    }
    
    public void setBloodGlucose(double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
        checkBloodGlucose();
    }
    
    public void setOxygenSaturation(double oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
        checkOxygenSaturation();
    }
    
    private void checkHeartRate() {
        boolean abnormal = heartRate < NORMAL_HEART_RATE_MIN || heartRate > NORMAL_HEART_RATE_MAX;
        abnormalFlags.put("heartRate", abnormal);
    }
    
    private void checkBloodPressure() {
        boolean abnormal = systolicBP < NORMAL_SYSTOLIC_MIN || systolicBP > NORMAL_SYSTOLIC_MAX ||
                          diastolicBP < NORMAL_DIASTOLIC_MIN || diastolicBP > NORMAL_DIASTOLIC_MAX;
        abnormalFlags.put("bloodPressure", abnormal);
    }
    
    private void checkTemperature() {
        boolean abnormal = temperature < NORMAL_TEMP_MIN || temperature > NORMAL_TEMP_MAX;
        abnormalFlags.put("temperature", abnormal);
    }
    
    private void checkBloodGlucose() {
        boolean abnormal = bloodGlucose < NORMAL_BLOOD_GLUCOSE_MIN || bloodGlucose > NORMAL_BLOOD_GLUCOSE_MAX;
        abnormalFlags.put("bloodGlucose", abnormal);
    }
    
    private void checkOxygenSaturation() {
        boolean abnormal = oxygenSaturation < NORMAL_O2_MIN || oxygenSaturation > NORMAL_O2_MAX;
        abnormalFlags.put("oxygenSaturation", abnormal);
    }
    
    public boolean isAbnormal() {
        return abnormalFlags.values().stream().anyMatch(flag -> flag);
    }
    
    public Map<String, Boolean> getAbnormalFlags() {
        return abnormalFlags;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public double getHeartRate() {
        return heartRate;
    }
    
    public int getSystolicBP() {
        return systolicBP;
    }
    
    public int getDiastolicBP() {
        return diastolicBP;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public double getBloodGlucose() {
        return bloodGlucose;
    }
    
    public double getOxygenSaturation() {
        return oxygenSaturation;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    @Override
    public String toString() {
        return "VitalSigns{" +
                "timestamp=" + timestamp +
                ", patient=" + patient.getFullName() +
                ", heartRate=" + heartRate +
                ", BP=" + systolicBP + "/" + diastolicBP +
                ", temp=" + temperature +
                ", abnormal=" + isAbnormal() +
                '}';
    }
}
