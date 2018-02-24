package com.attendance.report.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.attendance.report.domain.Report;
import com.attendance.report.domain.Report.Shift;

public interface ReportRepository extends MongoRepository<Report, String> {
	public List<Report> findByEmployeeIdAndShift(String employeeId, Shift shift);
	public List<Report> findDistinctReportByEmployeeIdAndShift(String employeeId, Shift shift);
}
