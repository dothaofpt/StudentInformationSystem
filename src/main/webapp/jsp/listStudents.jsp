<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Student Information System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Student List</h1>
<a href="${pageContext.request.contextPath}/addStudent">Add New Student</a> |
<a href="${pageContext.request.contextPath}/addScore">Add Score</a>
<table border="1">
    <tr>
        <th>Student Code</th>
        <th>Full Name</th>
        <th>Address</th>
        <th>Subject</th>
        <th>Score 1</th>
        <th>Score 2</th>
        <th>Grade</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <c:set var="rowspan" value="${student.studentScores.size() > 0 ? student.studentScores.size() : 1}"/>
        <c:set var="first" value="true"/>
        <c:if test="${empty student.studentScores}">
            <tr>
                <td rowspan="${rowspan}">${student.studentCode}</td>
                <td rowspan="${rowspan}">${student.fullName}</td>
                <td rowspan="${rowspan}">${student.address}</td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td>-</td>
                <td rowspan="${rowspan}">
                    <a href="${pageContext.request.contextPath}/editStudent?id=${student.studentId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/deleteStudent?id=${student.studentId}" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:if>
        <c:forEach var="score" items="${student.studentScores}">
            <tr>
                <c:if test="${first}">
                    <td rowspan="${rowspan}">${student.studentCode}</td>
                    <td rowspan="${rowspan}">${student.fullName}</td>
                    <td rowspan="${rowspan}">${student.address}</td>
                    <c:set var="first" value="false"/>
                </c:if>
                <td>${score.subject.subjectName}</td>
                <td>${score.score1}</td>
                <td>${score.score2}</td>
                <td>
                    <c:set var="grade" value="${0.3 * score.score1 + 0.7 * score.score2}"/>
                    <c:choose>
                        <c:when test="${grade >= 8.0}">A</c:when>
                        <c:when test="${grade >= 6.0}">B</c:when>
                        <c:when test="${grade >= 4.0}">D</c:when>
                        <c:otherwise>F</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/editScore?id=${score.studentScoreId}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/deleteScore?id=${score.studentScoreId}" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>