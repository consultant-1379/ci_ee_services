<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>ENIQ Events Services Authentication Successful</title>
	</head>
	<body>
    	<p align="center">
	    	<font size='5' color='blue'>You have successfully logged in to ENIQ Events Services.</font>
		</p>
		<hr>
		<form id="EniqEventsServicesLogout" method="POST" action='<%= response.encodeURL("logout.jsp") %>'>
			<table align="center">
				<tr align="center">
					<td colspan="2"><input type="submit" value="Logout"></td>
				</tr>
			</table>
    	</form>
	</body>
</html>
