package com.attendanceportal.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long leaveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "leave_start_date")
    private LocalDate leaveStartDate;

    @Column(name = "leave_end_date")
    private LocalDate leaveEndDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column(name = "requested_on")
    private LocalDateTime requestedOn;

    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    public LeaveRequest() {}

    // Getters and setters
    public Long getLeaveId() { return leaveId; }
    public void setLeaveId(Long leaveId) { this.leaveId = leaveId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public LocalDate getLeaveStartDate() { return leaveStartDate; }
    public void setLeaveStartDate(LocalDate leaveStartDate) { this.leaveStartDate = leaveStartDate; }
    public LocalDate getLeaveEndDate() { return leaveEndDate; }
    public void setLeaveEndDate(LocalDate leaveEndDate) { this.leaveEndDate = leaveEndDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public LeaveStatus getStatus() { return status; }
    public void setStatus(LeaveStatus status) { this.status = status; }
    public LocalDateTime getRequestedOn() { return requestedOn; }
    public void setRequestedOn(LocalDateTime requestedOn) { this.requestedOn = requestedOn; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
}
