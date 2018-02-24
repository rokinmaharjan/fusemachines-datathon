package com.attendance.report.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "things")
public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Shift {
		DAY, EVENING, NIGHT;
	}

	@Id
	private String id;
	private String employeeId;
	private long inTime;
	private long outTime;
	private String date;
	private String inTimeString;
	private String outTimeString;
	private Shift shift;
	private long inTimeEpochNow;
	private long outTimeEpochNow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public long getInTime() {
		return inTime;
	}

	public void setInTime(long inTime) {
		this.inTime = inTime;
	}

	public long getOutTime() {
		return outTime;
	}

	public void setOutTime(long outTime) {
		this.outTime = outTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInTimeString() {
		return inTimeString;
	}

	public void setInTimeString(String inTimeString) {
		this.inTimeString = inTimeString;
	}

	public String getOutTimeString() {
		return outTimeString;
	}

	public void setOutTimeString(String outTimeString) {
		this.outTimeString = outTimeString;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public long getInTimeEpoch() {
		return inTimeEpochNow;
	}

	public void setInTimeEpoch(long inTimeEpoch) {
		this.inTimeEpochNow = inTimeEpoch;
	}

	public long getOutTimeEpoch() {
		return outTimeEpochNow;
	}

	public void setOutTimeEpoch(long outTimeEpoch) {
		this.outTimeEpochNow = outTimeEpoch;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", employeeId=" + employeeId + ", inTime=" + inTime + ", outTime=" + outTime
				+ ", date=" + date + ", inTimeString=" + inTimeString + ", outTimeString=" + outTimeString + ", shift="
				+ shift + "]";
	}

}
