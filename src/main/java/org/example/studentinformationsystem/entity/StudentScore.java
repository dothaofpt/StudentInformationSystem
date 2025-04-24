package org.example.studentinformationsystem.entity;

public class StudentScore {
    private int studentScoreId;
    private Student student;
    private Subject subject;
    private double score1;
    private double score2;

    // Constructors
    public StudentScore() {}

    // Getters and Setters
    public int getStudentScoreId() { return studentScoreId; }
    public void setStudentScoreId(int studentScoreId) { this.studentScoreId = studentScoreId; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public double getScore1() { return score1; }
    public void setScore1(double score1) { this.score1 = score1; }

    public double getScore2() { return score2; }
    public void setScore2(double score2) { this.score2 = score2; }
}