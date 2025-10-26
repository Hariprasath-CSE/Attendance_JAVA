package com.example.Attendancejava.service.impl;

import com.example.Attendancejava.entity.LeaveRequest;
import com.example.Attendancejava.repository.LeaveRequestRepository;
import com.example.Attendancejava.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {
    
    private final LeaveRequestRepository leaveRequestRepository;

    @Override
    public LeaveRequest submitLeaveRequest(LeaveRequest leaveRequest) {
        leaveRequest.setStatus(LeaveRequest.LeaveStatus.PENDING);
        leaveRequest.setRequestedOn(LocalDateTime.now());
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest approveLeaveRequest(Long leaveId, String approvedBy) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
            .orElseThrow(() -> new RuntimeException("Leave request not found with id: " + leaveId));
        leaveRequest.setStatus(LeaveRequest.LeaveStatus.APPROVED);
        leaveRequest.setApprovedBy(approvedBy);
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest rejectLeaveRequest(Long leaveId, String rejectedBy) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
            .orElseThrow(() -> new RuntimeException("Leave request not found with id: " + leaveId));
        leaveRequest.setStatus(LeaveRequest.LeaveStatus.REJECTED);
        leaveRequest.setApprovedBy(rejectedBy);
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getStudentLeaveRequests(Long studentId) {
        return leaveRequestRepository.findByStudentStudentId(studentId);
    }

    @Override
    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus(LeaveRequest.LeaveStatus.PENDING);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }
}