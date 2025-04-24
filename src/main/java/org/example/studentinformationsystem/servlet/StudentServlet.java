package org.example.studentinformationsystem.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.studentinformationsystem.entity.Student;
import org.example.studentinformationsystem.service.StudentService;

import jakarta.inject.Inject;
import java.io.IOException;

@WebServlet(urlPatterns = {"/students", "/addStudent", "/editStudent", "/deleteStudent"})
public class StudentServlet extends HttpServlet {
    @Inject
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/addStudent":
                req.getRequestDispatcher("/jsp/addStudent.jsp").forward(req, resp);
                break;
            case "/editStudent":
                int studentId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("student", studentService.getStudentById(studentId));
                req.getRequestDispatcher("/jsp/editStudent.jsp").forward(req, resp);
                break;
            case "/deleteStudent":
                studentId = Integer.parseInt(req.getParameter("id"));
                studentService.deleteStudent(studentId);
                resp.sendRedirect("students");
                break;
            default:
                req.setAttribute("students", studentService.getAllStudents());
                req.getRequestDispatcher("/jsp/listStudents.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/addStudent".equals(action)) {
            String studentCode = req.getParameter("studentCode");
            String fullName = req.getParameter("fullName");
            String address = req.getParameter("address");

            Student student = new Student();
            student.setStudentCode(studentCode);
            student.setFullName(fullName);
            student.setAddress(address);

            studentService.addStudent(student);
        } else if ("/editStudent".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            String studentCode = req.getParameter("studentCode");
            String fullName = req.getParameter("fullName");
            String address = req.getParameter("address");

            Student student = studentService.getStudentById(studentId);
            student.setStudentCode(studentCode);
            student.setFullName(fullName);
            student.setAddress(address);

            studentService.updateStudent(student);
        }
        resp.sendRedirect("students");
    }
}