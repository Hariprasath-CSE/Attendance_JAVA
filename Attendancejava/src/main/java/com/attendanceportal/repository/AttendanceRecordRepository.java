package com.attendanceportal.repository;

import com.attendanceportal.model.AttendanceRecord;
import com.attendanceportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByStudent(Student student);
    List<AttendanceRecord> findByAttendanceDate(LocalDate date);
    List<AttendanceRecord> findByStudentStudentIdAndAttendanceDateBetween(Long studentId, LocalDate startDate, LocalDate endDate);
}
