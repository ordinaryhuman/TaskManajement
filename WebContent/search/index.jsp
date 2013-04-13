<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<%@ page import="com.tmj.model.Category" %>
<%@ page import="com.tmj.model.Task" %>
<%@ page import="com.tmj.model.Tag" %>
<%
	String typeQuery = (String) request.getParameter("typeQuery"); 
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Search Result</title>
<link rel='stylesheet' type="text/css" href="style/Design.css"/>
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/calendar.js"></script>
<script type="text/javascript" src="script/validation.js"></script>
<script type="text/javascript" src="script/searchresult.js"></script>
</head>

<body class="main">
<!-- Header -->
<jsp:include page="../pages/header.jsp"></jsp:include>

<!-- Content -->
<div class="TaskBoard">

	<div align="left">
		<% if(typeQuery.equals("all") || typeQuery.equals("kategori")) { %>
		<h4>Category</h4>
		<%
			Category[] categories = (Category[]) request.getAttribute("categories");
			for(Category category : categories) {
				out.println("<p>");
				out.println(String.format("Name     : %s <br>", category.getName()));
				out.println(String.format("Creator  : <a href='profile?username=%s'>%s</a> <br>", category.getCreator().getUsername(), category.getCreator().getUsername()));
				out.println("</p>");
			}
		%>
		<% } %>
		
		<% if(typeQuery.equals("all") || typeQuery.equals("task")  || typeQuery.equals("tag")) { %>
		<h4>Task</h4>
		<%
			Task[] tasks = (Task[]) request.getAttribute("tasks");
			for(Task task:tasks) {
				out.println("<p>");
				out.println(String.format("Nama      : <a href='%s'>%s</a> <br>", task.getID(), task.getTaskname()));
				out.println(String.format("Deadline  : %s <br>", task.getDeadline()));
				out.println(String.format("Tag       : "));
				Tag[] tags = task.getTags();
				for(Tag tag:tags) {
					out.print(String.format("%s,", tag.getTagname()));
				}
				out.println("<br>");
				out.println(String.format("Status    : "));
		%>
				<input type="checkbox" name="status" onclick="sendStatus(<% out.print(task.getID()); %>) <% out.print((task.getStatus()) ? "checked" : ""); %>">
		<%
				out.println("<br>");
				out.println("</p>");
			}
		%>
		<% } %>
		
		<% if(typeQuery.equals("all") || typeQuery.equals("user")) { %>
		<h4>User</h4>
		<%
			User[] users = (User[]) request.getAttribute("users");
			for(User user: users) {
				out.println("<p>");
				out.println(String.format("Username  : <a href='profile?username=%s'> %s </a> <br>", user.getUsername(), user.getUsername()));
				out.println(String.format("Fullname  : %s <br>", user.getFullname()));
				out.println("Avatar  : <br>");
				out.println(String.format("<img src='upload/avatars/%s'></img>", user.getAvatarPath()));
				out.println("</p>");
			}
		%>
		<% } %>
	</div>
   	
</div>

<!-- Footer -->
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>

</body>
</html>