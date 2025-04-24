package org.example.studentinformationsystem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentinformationsystem.dao.StudentDao;
import org.example.studentinformationsystem.dao.StudentScoreDao;
import org.example.studentinformationsystem.entity.Student;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/", "/students", "/addStudent", "/editStudent", "/deleteStudent"})
public class StudentServlet extends HttpServlet {
    private final StudentDao studentDao = new StudentDao();
    private final StudentScoreDao scoreDao = new StudentScoreDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/addStudent":
                    req.getRequestDispatcher("/jsp/addStudent.jsp").forward(req, resp);
                    break;
                case "/editStudent":
                    int studentId = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("student", studentDao.getStudentById(studentId));
                    req.getRequestDispatcher("/jsp/editStudent.jsp").forward(req, resp);
                    break;
                case "/deleteStudent":
                    studentId = Integer.parseInt(req.getParameter("id"));
                    studentDao.deleteStudent(studentId);
                    resp.sendRedirect("students");
                    break;
                default: // Handle both "/" and "/students"
                    for (Student student : studentDao.getAllStudents()) {
                        student.setStudentScores(scoreDao.getScoresByStudentId(student.getStudentId()));
                    }
                    req.setAttribute("students", studentDao.getAllStudents());
                    req.getRequestDispatcher("/jsp/listStudents.jsp").forward(req, resp);
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
            if ("/addStudent".equals(action)) {
                String studentCode = req.getParameter("studentCode");
                String fullName = req.getParameter("fullName");
                String address = req.getParameter("address");

                Student student = new Student();
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);

                studentDao.addStudent(student);
            } else if ("/editStudent".equals(action)) {
                int studentId = Integer.parseInt(req.getParameter("studentId"));
                String studentCode = req.getParameter("studentCode");
                String fullName = req.getParameter("fullName");
                String address = req.getParameter("address");

                Student student = new Student();
                student.setStudentId(studentId);
                student.setStudentCode(studentCode);
                student.setFullName(fullName);
                student.setAddress(address);

                studentDao.updateStudent(student);
            }
            resp.sendRedirect("students");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}