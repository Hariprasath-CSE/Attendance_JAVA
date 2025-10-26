package com.attendanceportal.controller;

import com.attendanceportal.model.LeaveRequest;
import com.attendanceportal.model.LeaveStatus;
import com.attendanceportal.model.Student;
import com.attendanceportal.service.LeaveRequestService;
import com.attendanceportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<LeaveRequest> submitLeaveRequest(@RequestBody Map<String, Object> data) {
        try {
            // Extract fields from the request
            Long studentId = Long.valueOf(data.get("studentId").toString());
            String startDateStr = data.get("leaveStartDate").toString();
            String endDateStr = data.get("leaveEndDate").toString();
            String reason = data.get("reason").toString();
            String statusStr = data.getOrDefault("status", "PENDING").toString();
            
            // Get the student
            Student student = studentService.getStudentById(studentId);
            
            // Create leave request
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setStudent(student);
            leaveRequest.setLeaveStartDate(LocalDate.parse(startDateStr));
            leaveRequest.setLeaveEndDate(LocalDate.parse(endDateStr));
            leaveRequest.setReason(reason);
            leaveRequest.setStatus(LeaveStatus.valueOf(statusStr));
            leaveRequest.setRequestedOn(LocalDateTime.now());
            
            LeaveRequest saved = leaveRequestService.saveLeaveRequest(leaveRequest);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByStudent(@PathVariable Long id) {
        return studentService.getStudentByIdOptional(id)
                .map(student -> ResponseEntity.ok(leaveRequestService.getLeaveRequestsByStudent(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id) {
        return leaveRequestService.getLeaveRequestById(id)
                .map(leave -> {
                    leave.setStatus(LeaveStatus.APPROVED);
                    return ResponseEntity.ok(leaveRequestService.saveLeaveRequest(leave));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id) {
        return leaveRequestService.getLeaveRequestById(id)
                .map(leave -> {
                    leave.setStatus(LeaveStatus.REJECTED);
                    return ResponseEntity.ok(leaveRequestService.saveLeaveRequest(leave));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
