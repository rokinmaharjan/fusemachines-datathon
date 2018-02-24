package com.attendance.report.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.report.service.ReportManager;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {
	@Autowired
	private ReportManager reportManager;
	
	@GetMapping("/format")
	public void formatInAndOutTime() {
		this.reportManager.formatStringDateToDate();
	}
	
	@GetMapping("/classifyShift")
	public void classifyShift() {
		this.reportManager.classifyShift();
	}
	
	@GetMapping("/predictShift")
	public void predictShift() {
		this.reportManager.predictShift();
	}
	
	@GetMapping("/shift")
	public Map<String, Integer> calculateShiftCount() {
		this.reportManager.calculateShiftCount();
		return null;
	}
	
	@GetMapping("/todaysEpoch")
	public void calculateTodaysEpoch() {
		this.reportManager.calculateInAndOutUnixRelativeToToday();
	}
}
