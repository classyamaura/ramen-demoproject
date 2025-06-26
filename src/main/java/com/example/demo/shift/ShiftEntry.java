package com.example.demo.shift;

public class ShiftEntry {
    private int staffId;
    private String startTime;
    private String endTime;
    private String remarks;

    // Getters
    public int getStaffId() {
        return staffId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    // Setters
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
} 