package org.example.studentinformationsystem.entity;

public class Subject {
    private int subjectId;
    private String subjectName;

    // Constructors
    public Subject() {}

    // Getters and Setters
    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
}