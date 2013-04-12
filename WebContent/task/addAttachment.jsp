<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
	Task task = (Task) request.getAttribute("task");
	Attachment[] attachments = (Attachment[]) request.getAttribute("attachments");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Make a Task</title>
    <link rel='stylesheet' type="text/css" href="style/Design.css"/>
    <script type="text/javascript" src="script/global.js"> </script>
    <script type="text/javascript" src="script/validation.js"> </script>
    <script type="text/javascript" src="script/calendar.js"></script>
    <script type="text/javascript" src="script/addattachmenttugas.js"> </script>
</head>

<body class="main">

<!-- HEADER -->
<jsp:include page="../pages/header.jsp"></jsp:include>

<div class="task1">	
	<div class="task2">
		<div class="maketask">
			<div class="fieldhead">
				<h2>Add Attachment Task</h2>
				<h4><%= task.getTaskname()%></h4>
			</div>
			
            <p> Attachment: </p>
		    <div id="rincian-attachment">
		    <%
		    	if(attachments != null)
		    	for(Attachment attachment : attachments) {
		    		out.println(String.format("<div id='rincian-attachment-%d'>", attachment.getID()));
		    		if(attachment.getType().equals("file")) {
		    			out.println(String.format("<a href='%s' target='_blank'> %s </a>", attachment.getFilePath(), attachment.getFilename()));
		    			out.println("<br>");
		    		} else if(attachment.getType().equals("image")) {
		    			out.println(String.format("%s<br><img src='%s'></img>", attachment.getFilename(), attachment.getFilePath()));
		    			out.println("<br>");
		    		} else if(attachment.getType().equals("video")) {
		    			out.println(String.format("%s<br><video width='320' height='240' controls><source src='%s'></video>", 
		    					attachment.getFilename(), attachment.getFilePath()));
		    			out.println("<br>");
		    		}
		    		out.println(String.format("<input type='button' class='delete' value='Delete' onclick='deleteAttachment(%d)' hidden/>", attachment.getID()));
		    		out.println("<br><br>");
		    		out.println("</div>");
		    	}
		    %>
		    </div>
		    
		 	<form class="edit" id="rincian-attachment-form" action="task?action=addAttachmentFromAddTask&taskID=<%= task.getID() %>" method="post" enctype="multipart/form-data">
		      <input type="radio" id="rincian-attachment-type-file" name="rincian-attachment-type" value="file" onclick="setType(<%= task.getID() %>)" checked/> File <br>
		      <input type="radio" id="rincian-attachment-type-image" name="rincian-attachment-type" value="image" onclick="setType(<%= task.getID() %>)"/> Image <br>
		      <input type="radio" id="rincian-attachment-type-video" name="rincian-attachment-type" value="video" onclick="setType(<%= task.getID() %>)"/> Video <br>
		      <input type="file" id="rincian-attachment-path" name="rincian-attachment-path" onchange="setType(<%= task.getID() %>)"/>
		      <input type="submit" value="Submit"/>
		    </form>
		    
		    <div>
		   		<a href="task?taskid=<%= task.getID() %>"> DONE</a>
		    </div>
		</div>
	</div>
</div>

<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>
</body>
</html>