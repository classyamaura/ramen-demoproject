package com.example.demo.kintai;

import java.time.LocalDateTime;

public class KintaiEntity {

	private String name;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String workTime;

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

}
