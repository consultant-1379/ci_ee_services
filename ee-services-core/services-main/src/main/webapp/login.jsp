<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>ENIQ Events Services Login Page</title>
	</head>
	<body>
	    <p align="center">
		    <font size='5' color='blue'>Please Login:</font>
		</p>
		<hr>
		<form id="EniqEventsServicesLogin" method="POST" action='<%= response.encodeURL("welcome.jsp/j_security_check") %>'>
			<table align="center">
				<c:if test='${not empty param["retry"]}'>
		 			<tr>
	  				<td colspan='2' align='center'>
	  					<b>The user name and password you supplied are not valid. Please try again.</b>
	  				</td>
	 				</tr>
	 				<tr>
	  				<td>&nbsp;</td>
	 				</tr>
				</c:if>
				<tr>
					<td><label>User Name</label></td>
					<td><input type="text" name="j_username"></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td><input type="password" name="j_password"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" value="Login"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
