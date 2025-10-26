package com.example.Attendancejava.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.Attendancejava.entity.LeaveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long leaveId;

    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull(message = "Leave start date is required")
    @Column(name = "leave_start_date")
    private LocalDate leaveStartDate;

    @NotNull(message = "Leave end date is required")
    @Column(name = "leave_end_date")
    private LocalDate leaveEndDate;

    @NotBlank(message = "Reason is required")
    @Size(min = 10, max = 500, message = "Reason must be between 10 and 500 characters")
    @Column(name = "reason")
    private String reason;

    @NotNull(message = "Status is required")
    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column(name = "requested_on")
    private LocalDateTime requestedOn;

    @Column(name = "approved_by", length = 50)
    private String approvedBy;
}