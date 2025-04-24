<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Edit Score</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Edit Score</h1>
<form action="${pageContext.request.contextPath}/editScore" method="post">
    <input type="hidden" name="scoreId" value="${score.studentScoreId}">
    <label for="studentId">Student:</label>
    <select id="studentId" name="studentId" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.studentId}" ${student.studentId == score.student.studentId ? 'selected' : ''}>
                    ${student.fullName}
            </option>
        </c:forEach>
    </select><br>
    <label for="subjectId">Subject:</label>
    <select id="subjectId" name="subjectId" required>
        <c:forEach var="subject" items="${subjects}">
            <option value="${subject.subjectId}" ${subject.subjectId == score.subject.subjectId ? 'selected' : ''}>
                    ${subject.subjectName}
            </option>
        </c:forEach>
    </select><br>
    <label for="score1">Score 1:</label>
    <input type="number" id="score1" name="score1" step="0.01" value="${score.score1}" required><br>
    <label for="score2">Score 2:</label>
    <input type="number" id="score2" name="score2" step="0.01" value="${score.score2}" required><br>
    <button type="submit">Update Score</button>
</form>
<a href="${pageContext.request.contextPath}/students">Back to Student List</a>
</body>
</html>