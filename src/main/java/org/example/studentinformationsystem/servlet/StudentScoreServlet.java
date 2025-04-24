package org.example.studentinformationsystem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentinformationsystem.dao.StudentDao;
import org.example.studentinformationsystem.dao.SubjectDao;
import org.example.studentinformationsystem.dao.StudentScoreDao;
import org.example.studentinformationsystem.entity.StudentScore;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/scores", "/addScore", "/editScore", "/deleteScore"})
public class StudentScoreServlet extends HttpServlet {
    private final StudentScoreDao scoreDao = new StudentScoreDao();
    private final StudentDao studentDao = new StudentDao();
    private final SubjectDao subjectDao = new SubjectDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/addScore":
                    req.setAttribute("students", studentDao.getAllStudents());
                    req.setAttribute("subjects", subjectDao.getAllSubjects());
                    req.getRequestDispatcher("/jsp/addScore.jsp").forward(req, resp);
                    break;
                case "/editScore":
                    int scoreId = Integer.parseInt(req.getParameter("id"));
                    StudentScore score = scoreDao.getScoreById(scoreId);
                    if (score == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Score not found");
                        return;
                    }
                    req.setAttribute("score", score);
                    req.setAttribute("students", studentDao.getAllStudents());
                    req.setAttribute("subjects", subjectDao.getAllSubjects());
                    req.getRequestDispatcher("/jsp/editScore.jsp").forward(req, resp);
                    break;
                case "/deleteScore":
                    scoreId = Integer.parseInt(req.getParameter("id"));
                    scoreDao.deleteScore(scoreId);
                    resp.sendRedirect("students");
                    break;
                default:
                    resp.sendRedirect("students");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
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

                StudentScore score = new StudentScore();
                score.setStudent(studentDao.getStudentById(studentId));
                score.setSubject(subjectDao.getSubjectById(subjectId));
                score.setScore1(score1);
                score.setScore2(score2);

                scoreDao.addScore(score);
            } else if ("/editScore".equals(action)) {
                int scoreId = Integer.parseInt(req.getParameter("scoreId"));
                int studentId = Integer.parseInt(req.getParameter("studentId"));
                int subjectId = Integer.parseInt(req.getParameter("subjectId"));
                double score1 = Double.parseDouble(req.getParameter("score1"));
                double score2 = Double.parseDouble(req.getParameter("score2"));

                StudentScore score = scoreDao.getScoreById(scoreId);
                score.setStudent(studentDao.getStudentById(studentId));
                score.setSubject(subjectDao.getSubjectById(subjectId));
                score.setScore1(score1);
                score.setScore2(score2);

                scoreDao.updateScore(score);
            }
            resp.sendRedirect("students");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        }
    }
}