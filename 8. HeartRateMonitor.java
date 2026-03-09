package com.rhms;

import java.util.Random;

public class HeartRateMonitor extends WearableDevice {
    private Random random;
    
    public HeartRateMonitor(String deviceId, String model, String manufacturer) {
        super(deviceId, model, manufacturer);
        this.random = new Random();
    }
    
    @Override
    public VitalSigns readVitals(Patient patient) {
        VitalSigns vitals = new VitalSigns(patient);
        
        // Simulate heart rate reading
        int heartRate = 70 + random.nextInt(30); // Range: 70-100
        
        // Sometimes generate abnormal readings
        if (random.nextInt(10) < 2) { // 20% chance of abnormal
            heartRate = 120 + random.nextInt(30); // Tachycardia
        }
        
        vitals.setHeartRate(heartRate);
        
        return vitals;
    }
}
