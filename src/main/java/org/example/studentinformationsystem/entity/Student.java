package org.example.studentinformationsystem.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;
    private String studentCode;
    private String fullName;
    private String address;
    private List<StudentScore> studentScores = new ArrayList<>();

    // Constructors
    public Student() {}

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentCode() { return studentCode; }
    public void setStudentCode(String studentCode) { this.studentCode = studentCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<StudentScore> getStudentScores() { return studentScores; }
    public void setStudentScores(List<StudentScore> studentScores) { this.studentScores = studentScores; }
}