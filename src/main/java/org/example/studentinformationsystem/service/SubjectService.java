package org.example.studentinformationsystem.service;

import org.example.studentinformationsystem.dao.SubjectDao;
import org.example.studentinformationsystem.entity.Subject;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {
    private final SubjectDao subjectDao;

    public SubjectService() {
        this.subjectDao = new SubjectDao();
    }

    public List<Subject> getAllSubjects() throws SQLException {
        return subjectDao.getAllSubjects();
    }

    public Subject getSubjectById(int id) throws SQLException {
        return subjectDao.getSubjectById(id);
    }
}