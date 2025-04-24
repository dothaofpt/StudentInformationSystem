package org.example.studentinformationsystem.dao;

import org.example.studentinformationsystem.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentCode(rs.getString("student_code"));
                student.setFullName(rs.getString("full_name"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM student WHERE student_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentCode(rs.getString("student_code"));
                    student.setFullName(rs.getString("full_name"));
                    student.setAddress(rs.getString("address"));
                    return student;
                }
            }
        }
        return null;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (student_code, full_name, address) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentCode());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getAddress());
            stmt.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE student SET student_code = ?, full_name = ?, address = ? WHERE student_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentCode());
            stmt.setString(2, student.getFullName());
            stmt.setString(3, student.getAddress());
            stmt.setInt(4, student.getStudentId());
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM student WHERE student_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}