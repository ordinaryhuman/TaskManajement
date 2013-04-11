<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
	User user = (User) request.getSession().getAttribute("user");
	Task task = (Task) request.getAttribute("task");
	Tag[] tags = (Tag[]) request.getAttribute("tags");
	Comment[] comments = (Comment[]) request.getAttribute("comments");
	Attachment[] attachments = (Attachment[]) request.getAttribute("attachments");
	User[] assignees = (User[]) request.getAttribute("assignees");
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Task Detail</title>
<link rel='stylesheet' type="text/css" href="style/Design.css"/>
<script type="text/javascript" src="script/global.js"></script>
<script type="text/javascript" src="script/calendar.js"></script>
<script type="text/javascript" src="script/validation.js"></script>
<script type="text/javascript" src="script/rinciantugas.js"></script>
</head>

<body class="main">
<!-- Header -->

<jsp:include page="../pages/header.jsp"></jsp:include>

<!-- Content -->
<div class="TaskBoard">

<h2 align="center"><%= task.getTaskname() %></h2>
<div align="left">
   	<p>
   		<a>Deadline :</a> <br>
   		<a id="rincian-deadline"></a><br>
	   	<input type="date" name="deadline" id="rincianinput-deadline" value="<%= task.getDeadline() %>"><br>
	   	<% if(user.isUserCanEditTask(task.getID())) { %>
	   	<input type="button" class="edit" onclick="editDeadline(<%= task.getID() %>)" hidden value="Edit">
   	    <% } %>
   	    
   	    <br><a>Asignee :</a><br>
   	    <div id="rincian-assignee"> 
   	    <ul>
   	    	<%
   	    	for(User assignee : assignees) {
   	    		out.println(String.format("<li id='rincian-assignee-%s'><a href='profile?username=%s'>%s<a>", assignee.getUsername(), assignee.getUsername(), assignee.getFullname()));
   	    		if(!assignee.getUsername().equals(task.getUsername())) {
   	    			out.println(String.format("<input type='button' class='delete' value='Delete' onclick='deleteAssignee(\"%s\", %d)' hidden/>", assignee.getUsername(), task.getID()));
   	    		}
   	    		out.println("</li>");
   	    	}
   	    	%>
   	    </ul>
   	    </div>
   	    <% if(user.isUserCanEditTask(task.getID())) { %>
   	    <div id="rincian-assignee-edit" class="edit" hidden>
   	    	<input type="text" id="rincian-assignee-edit-username" list="rincian-assignee-edit-username-datalist"/>
   	    	<datalist id="rincian-assignee-edit-username-datalist"></datalist>
   	    	<input type="button" value="Submit" onclick="addAssignee(<%= task.getID() %>)"/>
   	    </div>
   	    <% } %>
   	    
   	    <br><a>Tag :</a><br>
   	    <div id="rincian-tag">
   	    <ul>
	   	    <%
	   	    	for(Tag tag : tags) {
	   	    		out.println(String.format("<li id='rincian-tag-%s'>%s", tag.getTagname(), tag.getTagname()));
	   	    		out.println(String.format("<input type='button' class='delete' value='Delete' onclick='deleteTag(\"%s\", %d)' hidden/>", tag.getTagname(), task.getID()));
	   	    		out.println("</li>");
	   	    	}
	   	    %>
   	    </ul>
   	    </div>
   	    <% if(user.isUserCanEditTask(task.getID())) { %>
   	    <div id="rincian-tag-edit" class="edit" hidden>
   	    	<input type="text" id="rincian-tag-edit-tagname" list="rincian-tag-edit-tagname-datalist"/>
   	    	<datalist id="rincian-tag-edit-tagname-datalist"></datalist>
   	    	<input type="button" value="Submit" onclick="addTag(<%= task.getID() %>)"/>
   	    </div>
   	    <% } %>
   	  	
   	  	<br><a>Status :<br>
	   	<input type="checkbox" name="status" 
	   	<% if(user.isUserCanEditTask(task.getID())) { %>
	   		onclick="sendStatus(<%= task.getID() %>)"
	   	<% } %>
	   		value="1" <%= task.getStatus() ? "checked" : "" %> >
	   	DONE
	   	<br>
    </p>
    
    <p> Attachment: </p>
    <div id="rincian-attachment">
    <%
    	for(Attachment attachment : attachments) {
    		out.println(String.format("<div id='rincian-attachment-%d'>", attachment.getID()));
    		if(attachment.getType().equals("file")) {
    			out.println(String.format("<a href='%s'> %s </a>", attachment.getFilePath(), attachment.getFilename()));
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
    
	<br><a>Comment :</a><br> 
	<ul>
		<!--
			$comments = $task->getComments();
			
			foreach ($comments as $comment) {
				if ($comment->taskid == $task->id) {
					printf("<li id='rincian-asignee-%s'>%s ", $comment->username, $comment->username);
					printf('<a class="delete" href="rinciantugas2.php?taskid=%s&action=delete&username=%s">(delete)</a>', $task->id, $user->username);
					printf("<li id='rincian-comment-%s'>%s ", $comment->i, $comment->content);
					printf("</li>");
				}
			}
		-->
		
	</ul>
	<% if(user.isUserCanEditTask(task.getID())) { %>
	<input type="button" value="Edit Task" class="buttonbox2" id="rincianbutton-edit" onclick="edittask()"/>
	<% } %>
</div>

<hr noshade="noshade" />

<form id="comment_form" action="#">
	<h3>Add a Comment</h3>
	<div>
   		<textarea rows="5" cols="61" id="rincian-comment-input"></textarea>
 	</div>
	<div>
   		<input type="button" value="Submit Comment!" class="submitbutton" onclick="sendComment(<%= task.getID() %>)" />
    </div>
</form>

<div id="rincian-comment-list">
	<%
	for(Comment comment : comments) {
		out.println(String.format("<div class='commentbox' id='rincian-comment-list-%d'>", comment.getID()));
		out.println(String.format("<img src='upload/avatars/%s' class='commentuser'/>", comment.getUser().getAvatarPath()));
		out.println(String.format("<div class='nameuser'>%s (%s)</div>", comment.getUsername(), comment.getTimestamp()));
		out.println(String.format("<div class='comment'>%s</div>", comment.getContent()));
		out.println("</div>");
	}
	%>
</div>

</div>

<!-- Footer -->
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>
<a id="rinciantugas-taskid"><?php echo $task->id; ?></a>

</body>
</html>
