package com.example.demo.shift;

import java.util.List;

public class DailyShiftUpdate {
    private String shiftDate;
    private List<ShiftEntry> shifts;

    // Getters
    public String getShiftDate() {
        return shiftDate;
    }

    public List<ShiftEntry> getShifts() {
        return shifts;
    }

    // Setters
    public void setShiftDate(String shiftDate) {
        this.shiftDate = shiftDate;
    }

    public void setShifts(List<ShiftEntry> shifts) {
        this.shifts = shifts;
    }
} 