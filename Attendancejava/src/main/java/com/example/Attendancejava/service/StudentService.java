package com.example.Attendancejava.service;

import com.example.Attendancejava.entity.Student;
import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void deleteStudent(Long id);
    boolean isEmailExists(String email);
}