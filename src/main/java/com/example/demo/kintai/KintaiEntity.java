package com.example.demo.kintai;

import java.time.LocalDateTime;

public class KintaiEntity {
	
	private Long id; 
	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String workTime;
	private int hourlyWage;
	private long workMinutes;

	// Getter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	private String workDurationStr;

	public String getWorkDurationStr() {
		return workDurationStr;
	}

	public void setWorkDurationStr(String workDurationStr) {
		this.workDurationStr = workDurationStr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(int hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public long getWorkMinutes() {
		return workMinutes;
	}

	public void setWorkMinutes(long workMinutes) {
		this.workMinutes = workMinutes;
	}

}
