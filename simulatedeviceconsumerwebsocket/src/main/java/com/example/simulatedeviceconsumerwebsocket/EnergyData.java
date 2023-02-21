package com.example.simulatedeviceconsumerwebsocket;

import lombok.ToString;

@ToString
public class EnergyData {
    private long timestamp;
    private String deviceId;
    private String measurementValue;

    public EnergyData() {
    }

    public EnergyData(long timestamp, String deviceId, String measurementValue) {
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.measurementValue = measurementValue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(String measurementValue) {
        this.measurementValue = measurementValue;
    }
}
