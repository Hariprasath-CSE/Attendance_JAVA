package com.example.Attendancejava.service.impl;

import com.example.Attendancejava.entity.AttendanceRecord;
import com.example.Attendancejava.repository.AttendanceRepository;
import com.example.Attendancejava.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    
    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceRecord markAttendance(AttendanceRecord attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<AttendanceRecord> getStudentAttendance(Long studentId) {
        return attendanceRepository.findByStudentStudentId(studentId);
    }

    @Override
    public List<AttendanceRecord> getStudentAttendanceBetweenDates(Long studentId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByStudentStudentIdAndAttendanceDateBetween(studentId, startDate, endDate);
    }

    @Override
    public List<AttendanceRecord> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}