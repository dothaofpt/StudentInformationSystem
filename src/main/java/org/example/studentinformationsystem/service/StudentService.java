package org.example.studentinformationsystem.service;

import org.example.studentinformationsystem.dao.StudentDao;
import org.example.studentinformationsystem.entity.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDao studentDao;

    public StudentService() {
        this.studentDao = new StudentDao();
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDao.getAllStudents();
    }

    public Student getStudentById(int id) throws SQLException {
        return studentDao.getStudentById(id);
    }

    public void addStudent(Student student) throws SQLException {
        studentDao.addStudent(student);
    }

    public void updateStudent(Student student) throws SQLException {
        studentDao.updateStudent(student);
    }

    public void deleteStudent(int id) throws SQLException {
        studentDao.deleteStudent(id);
    }
}