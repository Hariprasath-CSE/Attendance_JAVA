package com.example.Attendancejava.service;

import com.example.Attendancejava.entity.LeaveRequest;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest approveLeaveRequest(Long leaveId, String approvedBy);
    LeaveRequest rejectLeaveRequest(Long leaveId, String rejectedBy);
    List<LeaveRequest> getStudentLeaveRequests(Long studentId);
    List<LeaveRequest> getPendingLeaveRequests();
    void deleteLeaveRequest(Long id);
}