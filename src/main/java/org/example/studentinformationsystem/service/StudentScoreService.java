package org.example.studentinformationsystem.service;

import org.example.studentinformationsystem.dao.StudentScoreDao;
import org.example.studentinformationsystem.entity.Student;
import org.example.studentinformationsystem.entity.StudentScore;

import java.sql.SQLException;
import java.util.List;

public class StudentScoreService {
    private final StudentScoreDao scoreDao;

    public StudentScoreService() {
        this.scoreDao = new StudentScoreDao();
    }

    public List<StudentScore> getScoresByStudentId(int studentId) throws SQLException {
        return scoreDao.getScoresByStudentId(studentId);
    }

    public StudentScore getScoreById(int id) throws SQLException {
        return scoreDao.getScoreById(id);
    }

    public void addScore(StudentScore score) throws SQLException {
        scoreDao.addScore(score);
    }

    public void updateScore(StudentScore score) throws SQLException {
        scoreDao.updateScore(score);
    }

    public void deleteScore(int id) throws SQLException {
        scoreDao.deleteScore(id);
    }

    public void setScoresForStudent(Student student) throws SQLException {
        student.setStudentScores(scoreDao.getScoresByStudentId(student.getStudentId()));
    }
}