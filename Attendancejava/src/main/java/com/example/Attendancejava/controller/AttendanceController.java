package com.example.Attendancejava.controller;
import com.example.Attendancejava.entity.AttendanceRecord;
import com.example.Attendancejava.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceRecord> markAttendance(@RequestBody AttendanceRecord attendance) {
        return ResponseEntity.ok(attendanceService.markAttendance(attendance));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceRecord>> getStudentAttendance(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getStudentAttendance(studentId));
    }

    @GetMapping("/student/{studentId}/date-range")
    public ResponseEntity<List<AttendanceRecord>> getStudentAttendanceBetweenDates(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(
            attendanceService.getStudentAttendanceBetweenDates(studentId, startDate, endDate)
        );
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok().build();
    }
}