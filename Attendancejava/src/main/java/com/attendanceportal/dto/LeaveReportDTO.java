package com.attendanceportal.dto;

import java.time.LocalDate;

public class LeaveReportDTO {
    private Long studentId;
    private String studentName;
    private LocalDate from;
    private LocalDate to;
    private long totalLeaves;

    public LeaveReportDTO() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public LocalDate getFrom() { return from; }
    public void setFrom(LocalDate from) { this.from = from; }
    public LocalDate getTo() { return to; }
    public void setTo(LocalDate to) { this.to = to; }
    public long getTotalLeaves() { return totalLeaves; }
    public void setTotalLeaves(long totalLeaves) { this.totalLeaves = totalLeaves; }
}

