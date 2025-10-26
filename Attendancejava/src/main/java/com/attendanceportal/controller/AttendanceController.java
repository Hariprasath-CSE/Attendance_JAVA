package com.attendanceportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendanceportal.model.AttendanceRecord;
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
    public ResponseEntity<AttendanceRecord> markAttendance(@RequestBody AttendanceRecord record) {
        AttendanceRecord saved = attendanceRecordService.saveAttendance(record);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceByStudent(@PathVariable Long id) {
        return studentService.getStudentByIdOptional(id)
                .map(student -> ResponseEntity.ok(attendanceRecordService.getAttendanceByStudent(student)))
                .orElse(ResponseEntity.notFound().build());
    }
}
