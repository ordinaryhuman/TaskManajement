<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.tmj.model.*" %>
<%@ page import="org.json.*" %>
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
	
	Integer categoryID = 1;
	Task[] tasks = Task.getTasksFromCategoryID(categoryID);
	
	JSONArray retval = new JSONArray();
	
	try {
		for(int i = 0; i < tasks.length; i++) {
			JSONObject obj = new JSONObject();
			
			obj.append("ID", tasks[i].getID());
			obj.append("categoryID", tasks[i].getCategoryID());
			obj.append("username", tasks[i].getUsername());
			obj.append("taskname", tasks[i].getTaskname());
			obj.append("status", tasks[i].getStatus());
			obj.append("deadline", tasks[i].getDeadline());
			
			retval.put(i, obj);
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
	
	out.println(retval.toString());
%>
Test Servlet
</body>
</html>