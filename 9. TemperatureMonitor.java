package com.rhms;

import java.util.Random;

public class TemperatureMonitor extends WearableDevice {
    private Random random;
    
    public TemperatureMonitor(String deviceId, String model, String manufacturer) {
        super(deviceId, model, manufacturer);
        this.random = new Random();
    }
    
    @Override
    public VitalSigns readVitals(Patient patient) {
        VitalSigns vitals = new VitalSigns(patient);
        
        // Simulate temperature reading
        double temperature = 36.5 + (random.nextDouble() * 1.5); // Range: 36.5-38.0
        
        // Sometimes generate abnormal readings
        if (random.nextInt(10) < 2) { // 20% chance of fever
            temperature = 38.5 + (random.nextDouble() * 1.5); // Fever
        }
        
        vitals.setTemperature(temperature);
        
        return vitals;
    }
}
