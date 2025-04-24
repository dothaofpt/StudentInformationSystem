<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Student</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>Edit Student</h1>
<form action="${pageContext.request.contextPath}/editStudent" method="post">
    <input type="hidden" name="studentId" value="${student.studentId}">
    <label for="studentCode">Student Code:</label>
    <input type="text" id="studentCode" name="studentCode" value="${student.studentCode}" required><br>
    <label for="fullName">Full Name:</label>
    <input type="text" id="fullName" name="fullName" value="${student.fullName}" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${student.address}"><br>
    <button type="submit">Update Student</button>
</form>
<a href="${pageContext.request.contextPath}/students">Back to Student List</a>
</body>
</html>