package com.example.Attendancejava.entity;

import java.time.LocalDate;

import com.example.Attendancejava.entity.AttendanceStatus;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attendance_records")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @NotNull(message = "Student is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull(message = "Attendance date is required")
    @PastOrPresent(message = "Attendance date must be today or in the past")
    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @NotNull(message = "Status is required")
    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(name = "marked_by", length = 50)
    private String markedBy;
}