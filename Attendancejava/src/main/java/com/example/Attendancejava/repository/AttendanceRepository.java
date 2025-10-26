package com.example.Attendancejava.repository;

import com.example.Attendancejava.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByStudentStudentIdAndAttendanceDateBetween(
        Long studentId, LocalDate startDate, LocalDate endDate);
    
    List<AttendanceRecord> findByStudentStudentId(Long studentId);
    
    List<AttendanceRecord> findByAttendanceDate(LocalDate date);
}