package com.attendanceportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/students")
    public String students() {
        return "students";
    }
    
    @GetMapping("/attendance")
    public String attendance() {
        return "attendance";
    }
    
    @GetMapping("/leave-requests")
    public String leaveRequests() {
        return "leave-requests";
    }
}

