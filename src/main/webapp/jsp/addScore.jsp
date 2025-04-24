<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Add Score</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Add New Score</h2>
<form action="${pageContext.request.contextPath}/addScore" method="post">
    <label>Student:</label><br>
    <select name="studentId" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.studentId}">${student.fullName}</option>
        </c:forEach>
    </select><br>
    <label>Subject:</label><br>
    <select name="subjectId" required>
        <c:forEach var="subject" items="${subjects}">
            <option value="${subject.subjectId}">${subject.subjectName}</option>
        </c:forEach>
    </select><br>
    <label>Score 1:</label><br>
    <input type="number" step="0.1" name="score1" required><br>
    <label>Score 2:</label><br>
    <input type="number" step="0.1" name="score2" required><br>
    <button type="submit">Add Score</button>
</form>
<a href="${pageContext.request.contextPath}/students">Back to Student List</a>
</body>
</html>