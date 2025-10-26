package com.example.Attendancejava.repository;

import com.example.Attendancejava.entity.LeaveRequest;
import com.example.Attendancejava.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStudentStudentId(Long studentId);
    
    List<LeaveRequest> findByStatus(LeaveStatus status);
}

