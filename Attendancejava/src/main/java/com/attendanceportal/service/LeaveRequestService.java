package com.attendanceportal.service;

import com.attendanceportal.model.LeaveRequest;
import com.attendanceportal.model.Student;
import com.attendanceportal.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveRequestsByStudent(Student student) {
        return leaveRequestRepository.findByStudent(student);
    }

    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id);
    }
}
