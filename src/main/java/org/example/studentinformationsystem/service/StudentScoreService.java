package org.example.studentinformationsystem.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.studentinformationsystem.entity.StudentScore;

@ApplicationScoped
public class StudentScoreService {
    @PersistenceContext(unitName = "enterpriseApplicationPU")
    private EntityManager em;

    @Transactional
    public void addScore(StudentScore score) {
        em.persist(score);
    }

    public StudentScore getScoreById(int id) {
        return em.find(StudentScore.class, id);
    }

    @Transactional
    public void updateScore(StudentScore score) {
        em.merge(score);
    }

    @Transactional
    public void deleteScore(int id) {
        StudentScore score = getScoreById(id);
        if (score != null) {
            em.remove(score);
        }
    }
}