package com.example.demo.order;

public class WeightSensorEntity {
    private Long sensorId;
    private String sensorName;
    private String materialName;
    private Integer registeredWeight;
    private Integer thresholdWeight;

    public WeightSensorEntity(Long sensorId, String sensorName, String materialName, Integer registeredWeight, Integer thresholdWeight) {
        this.sensorId = sensorId;
        this.sensorName = sensorName;
        this.materialName = materialName;
        this.registeredWeight = registeredWeight;
        this.thresholdWeight = thresholdWeight;
    }

    // Getters and Setters
    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getRegisteredWeight() {
        return registeredWeight;
    }

    public void setRegisteredWeight(Integer registeredWeight) {
        this.registeredWeight = registeredWeight;
    }

    public Integer getThresholdWeight() {
        return thresholdWeight;
    }

    public void setThresholdWeight(Integer thresholdWeight) {
        this.thresholdWeight = thresholdWeight;
    }
} 