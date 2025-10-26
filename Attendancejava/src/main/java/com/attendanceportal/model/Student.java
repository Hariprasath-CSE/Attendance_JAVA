package com.attendanceportal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "contact_no", length = 20)
    private String contactNo;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceRecord> attendanceRecords;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequests;

    public Student() {}

    // Getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public LocalDateTime getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDateTime joinDate) { this.joinDate = joinDate; }
    public List<AttendanceRecord> getAttendanceRecords() { return attendanceRecords; }
    public void setAttendanceRecords(List<AttendanceRecord> attendanceRecords) { this.attendanceRecords = attendanceRecords; }
    public List<LeaveRequest> getLeaveRequests() { return leaveRequests; }
    public void setLeaveRequests(List<LeaveRequest> leaveRequests) { this.leaveRequests = leaveRequests; }
}
