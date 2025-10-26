package com.attendanceportal.controller;

import com.attendanceportal.dto.AttendanceReportDTO;
import com.attendanceportal.dto.LeaveReportDTO;
import com.attendanceportal.model.AttendanceRecord;
import com.attendanceportal.model.LeaveRequest;
import com.attendanceportal.model.Student;
import com.attendanceportal.service.AttendanceRecordService;
import com.attendanceportal.service.LeaveRequestService;
import com.attendanceportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private AttendanceRecordService attendanceRecordService;
    @Autowired
    private LeaveRequestService leaveRequestService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/attendance/student/{studentId}")
    public ResponseEntity<AttendanceReportDTO> attendanceReportForStudent(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        Student student = studentService.getStudentById(studentId);
        List<AttendanceRecord> records = attendanceRecordService.getAttendanceByStudentBetweenDates(studentId, from, to);
        AttendanceReportDTO dto = new AttendanceReportDTO();
        dto.setStudentId(studentId);
        dto.setStudentName(student.getName());
        dto.setFrom(from);
        dto.setTo(to);
        dto.setPresentCount(records.stream().filter(r -> r.getStatus() != null && r.getStatus().name().equals("PRESENT")).count());
        dto.setAbsentCount(records.stream().filter(r -> r.getStatus() != null && r.getStatus().name().equals("ABSENT")).count());
        dto.setLateCount(records.stream().filter(r -> r.getStatus() != null && r.getStatus().name().equals("LATE")).count());
        dto.setExcusedCount(records.stream().filter(r -> r.getStatus() != null && r.getStatus().name().equals("EXCUSED")).count());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/leave/student/{studentId}")
    public ResponseEntity<LeaveReportDTO> leaveReportForStudent(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        Student student = studentService.getStudentById(studentId);
        List<LeaveRequest> leaves = leaveRequestService.getLeaveRequestsByStudent(student);
        long count = leaves.stream().filter(l -> {
            return (l.getLeaveStartDate() != null && !l.getLeaveStartDate().isAfter(to))
                    && (l.getLeaveEndDate() != null && !l.getLeaveEndDate().isBefore(from));
        }).count();
        LeaveReportDTO dto = new LeaveReportDTO();
        dto.setStudentId(studentId);
        dto.setStudentName(student.getName());
        dto.setFrom(from);
        dto.setTo(to);
        dto.setTotalLeaves(count);
        return ResponseEntity.ok(dto);
    }
}

