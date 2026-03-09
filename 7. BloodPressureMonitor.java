package com.rhms;

import java.util.Random;

public class BloodPressureMonitor extends WearableDevice {
    private Random random;
    
    public BloodPressureMonitor(String deviceId, String model, String manufacturer) {
        super(deviceId, model, manufacturer);
        this.random = new Random();
    }
    
    @Override
    public VitalSigns readVitals(Patient patient) {
        VitalSigns vitals = new VitalSigns(patient);
        
        // Simulate blood pressure reading with potential abnormal values
        int systolic = 110 + random.nextInt(40); // Range: 110-150
        int diastolic = 70 + random.nextInt(30); // Range: 70-100
        
        // Sometimes generate abnormal readings for demonstration
        if (random.nextInt(10) < 3) { // 30% chance of abnormal
            systolic = 160 + random.nextInt(30); // High BP
            diastolic = 100 + random.nextInt(20);
        }
        
        vitals.setBloodPressure(systolic, diastolic);
        
        return vitals;
    }
}
