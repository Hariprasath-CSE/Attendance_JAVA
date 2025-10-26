package com.attendanceportal.service;

import com.attendanceportal.model.AttendanceRecord;
import com.attendanceportal.model.Student;
import com.attendanceportal.repository.AttendanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    public AttendanceRecord saveAttendance(AttendanceRecord record) {
        return attendanceRecordRepository.save(record);
    }

    public List<AttendanceRecord> getAttendanceByStudent(Student student) {
        return attendanceRecordRepository.findByStudent(student);
    }

    public List<AttendanceRecord> getAttendanceByDate(LocalDate date) {
        return attendanceRecordRepository.findByAttendanceDate(date);
    }

    public List<AttendanceRecord> getAttendanceByStudentBetweenDates(Long studentId, LocalDate start, LocalDate end) {
        return attendanceRecordRepository.findByStudentStudentIdAndAttendanceDateBetween(studentId, start, end);
    }

    public Optional<AttendanceRecord> getById(Long id) { 
        return attendanceRecordRepository.findById(id); 
    }

    public void deleteById(Long id) { 
        attendanceRecordRepository.deleteById(id); 
    }
}
