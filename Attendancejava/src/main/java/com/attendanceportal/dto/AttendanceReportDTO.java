package com.attendanceportal.dto;

import java.time.LocalDate;

public class AttendanceReportDTO {
    private Long studentId;
    private String studentName;
    private LocalDate from;
    private LocalDate to;
    private long presentCount;
    private long absentCount;
    private long lateCount;
    private long excusedCount;

    public AttendanceReportDTO() {}

    // getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public LocalDate getFrom() { return from; }
    public void setFrom(LocalDate from) { this.from = from; }
    public LocalDate getTo() { return to; }
    public void setTo(LocalDate to) { this.to = to; }
    public long getPresentCount() { return presentCount; }
    public void setPresentCount(long presentCount) { this.presentCount = presentCount; }
    public long getAbsentCount() { return absentCount; }
    public void setAbsentCount(long absentCount) { this.absentCount = absentCount; }
    public long getLateCount() { return lateCount; }
    public void setLateCount(long lateCount) { this.lateCount = lateCount; }
    public long getExcusedCount() { return excusedCount; }
    public void setExcusedCount(long excusedCount) { this.excusedCount = excusedCount; }
}

