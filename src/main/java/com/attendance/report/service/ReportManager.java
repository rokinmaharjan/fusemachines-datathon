package com.attendance.report.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.report.domain.Report;
import com.attendance.report.domain.Report.Shift;
import com.attendance.report.repository.ReportRepository;
import com.attendance.utils.DateUtils;

@Service
public class ReportManager {
	
	@Autowired
	private ReportRepository reportRepository;

	public void formatStringDateToDate() {
		List<Report> reports = this.reportRepository.findAll();
		
		for(Report report : reports) {
			Date inTime = DateUtils.convertStringToDateTime(report.getDate().concat(" ").concat(report.getInTimeString()));
			report.setInTime(inTime.getTime());
			
			Date outTime = DateUtils.convertStringToDateTime(report.getDate().concat(" ").concat(report.getOutTimeString()));
			report.setOutTime(outTime.getTime());
		}
		
		this.reportRepository.saveAll(reports);
	}
	
	public void classifyShift() {
		List<Report> reports = this.reportRepository.findAll();
		
		for (Report report : reports) {
			String inTimePrefix = report.getInTimeString().split(":")[0];
			String outTimePrefix = report.getOutTimeString().split(":")[0];
			
			if((inTimePrefix.equals("7") || inTimePrefix.equals("8") || inTimePrefix.equals("9") || inTimePrefix.equals("10") || inTimePrefix.equals("11") || inTimePrefix.equals("12"))
					&& (outTimePrefix.equals("17") || outTimePrefix.equals("18") || outTimePrefix.equals("19") || outTimePrefix.equals("20"))) 
			{
				report.setShift(Shift.DAY);
			}
			else if (inTimePrefix.equals("12") || inTimePrefix.equals("13") || inTimePrefix.equals("14") || inTimePrefix.equals("15") 
					&& (outTimePrefix.equals("21") || outTimePrefix.equals("22") || outTimePrefix.equals("23"))){
				report.setShift(Shift.EVENING);
			}
			else if (inTimePrefix.equals("2") || inTimePrefix.equals("3") || inTimePrefix.equals("4") || inTimePrefix.equals("5") 
					&& (outTimePrefix.equals("17") || outTimePrefix.equals("18") || outTimePrefix.equals("19") )){
				report.setShift(Shift.NIGHT);
			}
		}
		this.reportRepository.saveAll(reports);
		
	}
	
	public void predictShift() {
		List<Report> reports = this.reportRepository.findAll();
		
		for (Report report : reports) {
			int employeeDayReports = this.reportRepository.findByEmployeeIdAndShift(report.getEmployeeId(), Shift.DAY).size();
			int employeeEveningReports = this.reportRepository.findByEmployeeIdAndShift(report.getEmployeeId(), Shift.EVENING).size();
			int employeeNightReports = this.reportRepository.findByEmployeeIdAndShift(report.getEmployeeId(), Shift.NIGHT).size();
			
			if ((employeeDayReports > employeeEveningReports) && (employeeDayReports > employeeNightReports)) {
				report.setShift(Shift.DAY);
			} else if ((employeeEveningReports> employeeDayReports) && (employeeEveningReports > employeeNightReports)) {
				report.setShift(Shift.EVENING);
			} else {
				report.setShift(Shift.NIGHT);
			}
		}
		
		this.reportRepository.saveAll(reports);
	}
	
	
	public void calculateShiftCount() {
		
//		List<Report> reports = this.reportRepository.findAll();
////		Integer dayCount = this.reportRepository.findDistinctReportByEmployeeIdAndShift(Shift.DAY).size();
////		Integer eveningCount = this.reportRepository.findDistinctReportByEmployeeIdAndShift(Shift.EVENING).size();
////		Integer nightCount = this.reportRepository.findDistinctReportByEmployeeIdAndShift(Shift.NIGHT).size();
//		
//		
//		for (Report report : reports) {
//			System.err.println(this.reportRepository.findDistinctReportByEmployeeIdAndShift(report.getEmployeeId(), Shift.DAY).size();
//		}
//		
//		System.err.println(dayCount);
//		System.err.println(eveningCount);
//		System.err.println(nightCount);
	}
	
	public void calculateInAndOutUnixRelativeToToday() {
		List<Report> reports = this.reportRepository.findAll();

		for (Report report : reports) {
			long inTimeEpochNow = DateUtils.convertStringToDateTime("2018-02-24" + " " + report.getInTimeString()).getTime();
			long outTimeEpochNow = DateUtils.convertStringToDateTime("2018-02-24" + " " + report.getOutTimeString()).getTime();
			
			report.setInTimeEpoch(inTimeEpochNow);
			report.setOutTimeEpoch(outTimeEpochNow);
		}
		
		this.reportRepository.saveAll(reports);
	}
	
	
}
