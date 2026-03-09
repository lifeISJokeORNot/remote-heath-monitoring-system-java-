package com.rhms;

public abstract class WearableDevice {
    private String deviceId;
    private String model;
    private String manufacturer;
    private Patient patient;
    private boolean active;
    
    public WearableDevice(String deviceId, String model, String manufacturer) {
        this.deviceId = deviceId;
        this.model = model;
        this.manufacturer = manufacturer;
        this.active = true;
    }
    
    public abstract VitalSigns readVitals(Patient patient);
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public String getDeviceId() { return deviceId; }
    
    public String getModel() { return model; }
    
    public String getManufacturer() { return manufacturer; }
    
    public Patient getPatient() { return patient; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    @Override
    public String toString() {
        return "WearableDevice{" +
                "deviceId='" + deviceId + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
