package com.example.Attendancejava.service;

import com.example.Attendancejava.entity.AttendanceRecord;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    AttendanceRecord markAttendance(AttendanceRecord attendance);
    List<AttendanceRecord> getStudentAttendance(Long studentId);
    List<AttendanceRecord> getStudentAttendanceBetweenDates(Long studentId, LocalDate startDate, LocalDate endDate);
    List<AttendanceRecord> getAttendanceByDate(LocalDate date);
    void deleteAttendance(Long id);
}