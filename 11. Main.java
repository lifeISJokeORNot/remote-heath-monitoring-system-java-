package com.rhms;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Remote Health Monitoring System Demonstration ===\n");
        
        // Create the monitoring system
        MonitoringSystem system = new MonitoringSystem("RHMS-001");
        
        // Create doctors
        Doctor doctor1 = new Doctor("D001", "Sarah", "Johnson", "sarah.j@hospital.com", 
                                    "555-0101", "LIC-12345", "Cardiology");
        Doctor doctor2 = new Doctor("D002", "Michael", "Chen", "michael.c@hospital.com", 
                                    "555-0102", "LIC-67890", "Endocrinology");
        
        // Register doctors
        system.registerDoctor(doctor1);
        system.registerDoctor(doctor2);
        
        // Create patients
        Patient patient1 = new Patient("P001", "John", "Smith", "john.s@email.com", 
                                       "555-0201", "MRN-1001");
        patient1.setDateOfBirth(LocalDate.of(1965, 3, 15));
        patient1.addChronicCondition("Hypertension");
        patient1.addChronicCondition("Type 2 Diabetes");
        
        Patient patient2 = new Patient("P002", "Mary", "Williams", "mary.w@email.com", 
                                       "555-0202", "MRN-1002");
        patient2.setDateOfBirth(LocalDate.of(1972, 8, 22));
        patient2.addChronicCondition("Hypertension");
        
        // Register patients
        system.registerPatient(patient1);
        system.registerPatient(patient2);
        
        // Assign doctors to patients
        patient1.assignDoctor(doctor1); // Dr. Johnson for John (cardiology)
        patient2.assignDoctor(doctor2); // Dr. Chen for Mary
        
        System.out.println("\n--- Patient-Doctor Assignments ---");
        System.out.println(patient1.getFullName() + " assigned to Dr. " + 
                          patient1.getAssignedDoctor().getLastName());
        System.out.println(patient2.getFullName() + " assigned to Dr. " + 
                          patient2.getAssignedDoctor().getLastName());
        
        // Create and assign wearable devices
        BloodPressureMonitor bpMonitor = new BloodPressureMonitor("BP-001", "BP-Pro", "MediTech");
        HeartRateMonitor hrMonitor = new HeartRateMonitor("HR-001", "HR-Plus", "MediTech");
        TemperatureMonitor tempMonitor = new TemperatureMonitor("TEMP-001", "Temp-Sense", "HealthGear");
        
        patient1.assignDevice(bpMonitor);
        patient2.assignDevice(hrMonitor);
        
        System.out.println("\n--- Devices Assigned ---");
        System.out.println(patient1.getFullName() + " using: " + bpMonitor.getModel());
        System.out.println(patient2.getFullName() + " using: " + hrMonitor.getModel());
        
        // Simulate vital signs readings
        System.out.println("\n--- Simulating Vital Signs Readings ---");
        
        // Reading 1 for Patient 1 (Blood Pressure)
        VitalSigns vitals1 = bpMonitor.readVitals(patient1);
        // Add other vitals manually for completeness
        vitals1.setHeartRate(72);
        vitals1.setTemperature(36.8);
        vitals1.setBloodGlucose(110);
        vitals1.setOxygenSaturation(98);
        
        system.processVitalSigns(patient1, vitals1);
        System.out.println("Reading for " + patient1.getFullName() + ": " + 
                          vitals1.getSystolicBP() + "/" + vitals1.getDiastolicBP() + 
                          " mmHg, Abnormal: " + vitals1.isAbnormal());
        
        // Reading 2 for Patient 2 (Heart Rate)
        VitalSigns vitals2 = hrMonitor.readVitals(patient2);
        vitals2.setBloodPressure(118, 76);
        vitals2.setTemperature(36.6);
        vitals2.setBloodGlucose(95);
        vitals2.setOxygenSaturation(97);
        
        system.processVitalSigns(patient2, vitals2);
        System.out.println("Reading for " + patient2.getFullName() + ": HR=" + 
                          vitals2.getHeartRate() + " bpm, Abnormal: " + vitals2.isAbnormal());
        
        // Reading 3 for Patient 1 (simulate abnormal reading)
        System.out.println("\n--- Simulating Abnormal Reading ---");
        VitalSigns vitals3 = bpMonitor.readVitals(patient1);
        vitals3.setHeartRate(85);
        vitals3.setTemperature(37.2);
        vitals3.setBloodGlucose(195); // High blood glucose
        vitals3.setOxygenSaturation(92); // Low oxygen saturation
        
        system.processVitalSigns(patient1, vitals3);
        System.out.println("Abnormal reading for " + patient1.getFullName() + ": " +
                          "Glucose=" + vitals3.getBloodGlucose() + 
                          " mg/dL, O2=" + vitals3.getOxygenSaturation() + "%");
        
        // Doctor reviews alerts
        System.out.println("\n--- Doctor Review Process ---");
        Doctor attendingDoctor = patient1.getAssignedDoctor();
        
        // Get the latest alert for patient1
        if (!patient1.getAlerts().isEmpty()) {
            Alert latestAlert = patient1.getAlerts().get(patient1.getAlerts().size() - 1);
            attendingDoctor.reviewAlert(latestAlert, 
                "Please monitor blood glucose levels and schedule a follow-up appointment. " +
                "Consider adjusting insulin dosage.");
            
            // Schedule follow-up
            attendingDoctor.scheduleFollowUp(patient1, "2024-01-20 10:30 AM");
        }
        
        // Generate system report
        system.generateReport();
        
        // Demonstrate polymorphism with devices
        System.out.println("\n--- Device Polymorphism Demonstration ---");
        WearableDevice[] devices = {
            new BloodPressureMonitor("BP-002", "BP-Pro Plus", "MediTech"),
            new HeartRateMonitor("HR-002", "HR-Elite", "MediTech"),
            new TemperatureMonitor("TEMP-002", "Temp-Sense Pro", "HealthGear")
        };
        
        for (WearableDevice device : devices) {
            VitalSigns vitals = device.readVitals(patient2);
            System.out.println(device.getClass().getSimpleName() + " reading: " + 
                              (vitals.isAbnormal() ? "Abnormal" : "Normal"));
        }
        
        System.out.println("\n=== Demonstration Complete ===");
    }
}
