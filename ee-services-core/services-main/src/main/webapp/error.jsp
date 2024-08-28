<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
    out.println("{\"success\" : \"false\", \"errorDescription\" : \"HTTP " + response.getStatus() + " status returned. Please refer to server.log for more details.\"}");
%>
