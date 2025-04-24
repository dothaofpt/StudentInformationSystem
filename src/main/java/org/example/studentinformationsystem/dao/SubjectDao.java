package org.example.studentinformationsystem.dao;

import org.example.studentinformationsystem.entity.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    public List<Subject> getAllSubjects() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subjects.add(subject);
            }
        }
        return subjects;
    }

    public Subject getSubjectById(int id) throws SQLException {
        String sql = "SELECT * FROM subject WHERE subject_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSubjectId(rs.getInt("subject_id"));
                    subject.setSubjectName(rs.getString("subject_name"));
                    return subject;
                }
            }
        }
        return null;
    }
}