package org.example.studentinformationsystem.service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.studentinformationsystem.entity.Subject;

import java.util.List;

public class SubjectService {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public Subject getSubjectById(int subjectId) {
        return entityManager.find(Subject.class, subjectId);
    }

    @Transactional
    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }
}