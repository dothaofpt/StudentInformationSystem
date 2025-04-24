package org.example.studentinformationsystem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentinformationsystem.entity.Student;
import org.example.studentinformationsystem.entity.Subject;
import org.example.studentinformationsystem.entity.StudentScore;
import org.example.studentinformationsystem.service.StudentService;
import org.example.studentinformationsystem.service.SubjectService;
import org.example.studentinformationsystem.service.StudentScoreService;

import jakarta.inject.Inject;
import java.io.IOException;

@WebServlet(urlPatterns = {"/scores", "/addScore", "/editScore", "/deleteScore"})
public class StudentScoreServlet extends HttpServlet {
    @Inject
    private StudentScoreService scoreService;
    @Inject
    private StudentService studentService;
    @Inject
    private SubjectService subjectService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/addScore":
                req.setAttribute("students", studentService.getAllStudents());
                req.setAttribute("subjects", subjectService.getAllSubjects());
                req.getRequestDispatcher("/jsp/addScore.jsp").forward(req, resp);
                break;
            case "/editScore":
                int scoreId = Integer.parseInt(req.getParameter("id"));
                StudentScore score = scoreService.getScoreById(scoreId);
                if (score == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Score not found");
                    return;
                }
                req.setAttribute("score", score);
                req.setAttribute("students", studentService.getAllStudents());
                req.setAttribute("subjects", subjectService.getAllSubjects());
                req.getRequestDispatcher("/jsp/editScore.jsp").forward(req, resp);
                break;
            case "/deleteScore":
                scoreId = Integer.parseInt(req.getParameter("id"));
                scoreService.deleteScore(scoreId);
                resp.sendRedirect("students");
                break;
            default:
                resp.sendRedirect("students");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            if ("/addScore".equals(action)) {
                int studentId = Integer.parseInt(req.getParameter("studentId"));
                int subjectId = Integer.parseInt(req.getParameter("subjectId"));
                double score1 = Double.parseDouble(req.getParameter("score1"));
                double score2 = Double.parseDouble(req.getParameter("score2"));

                Student student = studentService.getStudentById(studentId);
                Subject subject = subjectService.getSubjectById(subjectId);

                if (student == null || subject == null) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student or subject ID");
                    return;
                }

                StudentScore score = new StudentScore();
                score.setStudent(student);
                score.setSubject(subject);
                score.setScore1(score1);
                score.setScore2(score2);

                scoreService.addScore(score);
            } else if ("/editScore".equals(action)) {
                int scoreId = Integer.parseInt(req.getParameter("scoreId"));
                int studentId = Integer.parseInt(req.getParameter("studentId"));
                int subjectId = Integer.parseInt(req.getParameter("subjectId"));
                double score1 = Double.parseDouble(req.getParameter("score1"));
                double score2 = Double.parseDouble(req.getParameter("score2"));

                StudentScore score = scoreService.getScoreById(scoreId);
                if (score == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Score not found");
                    return;
                }

                Student student = studentService.getStudentById(studentId);
                Subject subject = subjectService.getSubjectById(subjectId);

                if (student == null || subject == null) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student or subject ID");
                    return;
                }

                score.setStudent(student);
                score.setSubject(subject);
                score.setScore1(score1);
                score.setScore2(score2);

                scoreService.updateScore(score);
            }
            resp.sendRedirect("students");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        }
    }
}