<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>ENIQ Events Services Authentication Successful</title>
	</head>

	<body>
		<% request.getSession(true).invalidate(); response.sendRedirect("login.jsp"); %>
	</body>
</html>