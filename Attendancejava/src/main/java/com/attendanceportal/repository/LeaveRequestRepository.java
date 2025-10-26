package com.attendanceportal.repository;

import com.attendanceportal.model.LeaveRequest;
import com.attendanceportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStudent(Student student);
}
