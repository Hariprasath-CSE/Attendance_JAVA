package com.attendanceportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendanceportal.model.AttendanceRecord;
import com.attendanceportal.model.AttendanceStatus;
import com.attendanceportal.model.Student;
import com.attendanceportal.service.AttendanceRecordService;
import com.attendanceportal.service.StudentService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceRecordService attendanceRecordService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<AttendanceRecord> markAttendance(@RequestBody Map<String, Object> data) {
        try {
            // Extract fields from the request
            Long studentId = Long.valueOf(data.get("studentId").toString());
            String dateStr = data.get("attendanceDate").toString();
            String statusStr = data.get("status").toString();
            String markedBy = data.get("markedBy").toString();
            
            // Get the student
            Student student = studentService.getStudentById(studentId);
            
            // Create attendance record
            AttendanceRecord record = new AttendanceRecord();
            record.setStudent(student);
            record.setAttendanceDate(java.time.LocalDate.parse(dateStr));
            record.setStatus(AttendanceStatus.valueOf(statusStr));
            record.setMarkedBy(markedBy);
            
            AttendanceRecord saved = attendanceRecordService.saveAttendance(record);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceByStudent(@PathVariable Long id) {
        return studentService.getStudentByIdOptional(id)
                .map(student -> ResponseEntity.ok(attendanceRecordService.getAttendanceByStudent(student)))
                .orElse(ResponseEntity.notFound().build());
    }
}
