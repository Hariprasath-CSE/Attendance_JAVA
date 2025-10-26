package com.attendanceportal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_records")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Column(name = "marked_by", length = 50)
    private String markedBy;

    public AttendanceRecord() {}

    // Getters and setters
    public Long getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Long attendanceId) { this.attendanceId = attendanceId; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }
    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }
    public String getMarkedBy() { return markedBy; }
    public void setMarkedBy(String markedBy) { this.markedBy = markedBy; }
}
