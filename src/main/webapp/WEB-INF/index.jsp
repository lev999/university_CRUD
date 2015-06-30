<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>University time table</title>
</head>
<body>
<div align="center">
	<div style="color: crimson">${alert_error}</div>
	<form:form action="register" method="post" commandName="userForm">
		<table border="0">
			<tr>
				<td colspan="2" align="center"><h2>University time table</h2></td>
			</tr>
			<tr>
				<td>Student Name:</td>
				<td><form:input path="name" /></td>
			</tr>


			<tr>
				<td>Course:</td>
				<td><form:select path="course" items="${courseList}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="action" value="Register student for course" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="action" value="Show all students on course" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="action" value="Show all courses of student" /></td>
			</tr>
		</table>
	</form:form>


	<br>
	<div >${alert}</div>
	<table  border="1" bordercolor="black">

		<tr>
			<td>Student name</td>
			<td>Course name</td>
			<td>Registration date</td>

		</tr>

			<c:forEach var="p" items="${students}">
				<tr>
					<td>${p.name}</td>
					<td>${p.course}</td>
					<td>${p.date}</td>
				</tr>
			</c:forEach>
	</table>
</div>

</body>
</html>


