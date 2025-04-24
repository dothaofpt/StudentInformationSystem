package org.example.studentinformationsystem.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.studentinformationsystem.entity.Student;

import java.util.List;

@ApplicationScoped
public class StudentService {
    @PersistenceContext(unitName = "enterpriseApplicationPU")
    private EntityManager em;

    @Transactional
    public void addStudent(Student student) {
        em.persist(student);
    }

    public List<Student> getAllStudents() {
        return em.createQuery("SELECT s FROM Student s LEFT JOIN FETCH s.studentScores", Student.class).getResultList();
    }

    public Student getStudentById(int id) {
        return em.find(Student.class, id);
    }

    @Transactional
    public void updateStudent(Student student) {
        em.merge(student);
    }

    @Transactional
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        if (student != null) {
            em.remove(student);
        }
    }
}