<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h2>Add New Student</h2>
<form action="${pageContext.request.contextPath}/addStudent" method="post">
    <label>Student Code:</label><br>
    <input type="text" name="studentCode" required><br>
    <label>Full Name:</label><br>
    <input type="text" name="fullName" required><br>
    <label>Address:</label><br>
    <input type="text" name="address" required><br>
    <button type="submit">Add Student</button>
</form>
<a href="${pageContext.request.contextPath}/students">Back to Student List</a>
</body>
</html>