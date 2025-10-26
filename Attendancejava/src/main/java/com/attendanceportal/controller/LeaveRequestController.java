package com.attendanceportal.controller;

import com.attendanceportal.model.LeaveRequest;
import com.attendanceportal.model.Student;
import com.attendanceportal.model.LeaveStatus;
import com.attendanceportal.service.LeaveRequestService;
import com.attendanceportal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<LeaveRequest> submitLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        LeaveRequest saved = leaveRequestService.saveLeaveRequest(leaveRequest);
        return ResponseEntity.ok(saved);
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
