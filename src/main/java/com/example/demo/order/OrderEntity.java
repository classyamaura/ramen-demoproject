package com.example.demo.order;

public class OrderEntity {
    private Long id;
    private String name;
    private Integer weight;
    private Integer registeredWeightFromSensor; // WEIGHT_SENSORSから取得する登録重さ
    private Double calculatedUnit; // 計算結果
    private String statusSymbol; // 新しく追加するステータスシンボル

    public OrderEntity(Long id, String name, Integer weight, Integer registeredWeightFromSensor, Double calculatedUnit, String statusSymbol) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.registeredWeightFromSensor = registeredWeightFromSensor;
        this.calculatedUnit = calculatedUnit;
        this.statusSymbol = statusSymbol;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getRegisteredWeightFromSensor() {
        return registeredWeightFromSensor;
    }

    public void setRegisteredWeightFromSensor(Integer registeredWeightFromSensor) {
        this.registeredWeightFromSensor = registeredWeightFromSensor;
    }

    public Double getCalculatedUnit() {
        return calculatedUnit;
    }

    public void setCalculatedUnit(Double calculatedUnit) {
        this.calculatedUnit = calculatedUnit;
    }

    public String getStatusSymbol() {
        return statusSymbol;
    }

    public void setStatusSymbol(String statusSymbol) {
        this.statusSymbol = statusSymbol;
    }
}
