<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Servlet</title>
</head>
<body>
<%
	ResultSet result = (ResultSet) request.getAttribute("result");
	out.println(String.format("SELECT * FROM `%s` WHERE `username` LIKE '%s'; <br>", "user", "%" + "apaa" + "%"));
	if(result != null)
		while (result.next())
		{
			out.println(result.getString(1));
			out.println(result.getString(2));
			out.println(result.getString(3));
			out.println();
		}
	else
	out.println("gak nemu");
%>
Test Servlet
</body>
</html>