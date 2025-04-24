package org.example.studentinformationsystem.dao;

import org.example.studentinformationsystem.entity.Student;
import org.example.studentinformationsystem.entity.StudentScore;
import org.example.studentinformationsystem.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentScoreDao {
    private final StudentDao studentDao = new StudentDao();
    private final SubjectDao subjectDao = new SubjectDao();

    public List<StudentScore> getScoresByStudentId(int studentId) throws SQLException {
        List<StudentScore> scores = new ArrayList<>();
        String sql = "SELECT * FROM student_score WHERE student_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StudentScore score = new StudentScore();
                    score.setStudentScoreId(rs.getInt("student_score_id"));
                    score.setStudent(studentDao.getStudentById(rs.getInt("student_id")));
                    score.setSubject(subjectDao.getSubjectById(rs.getInt("subject_id")));
                    score.setScore1(rs.getDouble("score1"));
                    score.setScore2(rs.getDouble("score2"));
                    scores.add(score);
                }
            }
        }
        return scores;
    }

    public StudentScore getScoreById(int id) throws SQLException {
        String sql = "SELECT * FROM student_score WHERE student_score_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    StudentScore score = new StudentScore();
                    score.setStudentScoreId(rs.getInt("student_score_id"));
                    score.setStudent(studentDao.getStudentById(rs.getInt("student_id")));
                    score.setSubject(subjectDao.getSubjectById(rs.getInt("subject_id")));
                    score.setScore1(rs.getDouble("score1"));
                    score.setScore2(rs.getDouble("score2"));
                    return score;
                }
            }
        }
        return null;
    }

    public void addScore(StudentScore score) throws SQLException {
        String sql = "INSERT INTO student_score (student_id, subject_id, score1, score2) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, score.getStudent().getStudentId());
            stmt.setInt(2, score.getSubject().getSubjectId());
            stmt.setDouble(3, score.getScore1());
            stmt.setDouble(4, score.getScore2());
            stmt.executeUpdate();
        }
    }

    public void updateScore(StudentScore score) throws SQLException {
        String sql = "UPDATE student_score SET student_id = ?, subject_id = ?, score1 = ?, score2 = ? WHERE student_score_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, score.getStudent().getStudentId());
            stmt.setInt(2, score.getSubject().getSubjectId());
            stmt.setDouble(3, score.getScore1());
            stmt.setDouble(4, score.getScore2());
            stmt.setInt(5, score.getStudentScoreId());
            stmt.executeUpdate();
        }
    }

    public void deleteScore(int id) throws SQLException {
        String sql = "DELETE FROM student_score WHERE student_score_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}