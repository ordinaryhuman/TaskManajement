<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
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
	   	<input type="button" class="edit" onclick="editDeadline(<%= task.getID() %>)" hidden value="Edit">
   	    
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
   	    <div id="rincian-assignee-edit" class="edit" hidden>
   	    	<input type="text" id="rincian-assignee-edit-username" list="rincian-assignee-edit-username-datalist"/>
   	    	<datalist id="rincian-assignee-edit-username-datalist"></datalist>
   	    	<input type="button" value="Submit" onclick="addAssignee(<%= task.getID() %>)"/>
   	    </div>
   	    
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
   	    <div id="rincian-tag-edit" class="edit" hidden>
   	    	<input type="text" id="rincian-tag-edit-tagname" list="rincian-tag-edit-tagname-datalist"/>
   	    	<datalist id="rincian-tag-edit-tagname-datalist"></datalist>
   	    	<input type="button" value="Submit" onclick="addTag(<%= task.getID() %>)"/>
   	    </div>
   	  	
   	  	<br><a>Status :<br>
	   	<input type="checkbox" name="status" onclick="sendStatus(<%= task.getID() %>)" value="1" <%= task.getStatus() ? "checked" : "" %> > DONE<br>
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
	
	<input type="button" value="Edit Task" class="buttonbox2" id="rincianbutton-edit" onclick="edittask()"/>
</div>

<hr noshade="noshade" />

<form id="comment_form" action="#">
	<h3>Add a Comment</h3>
	<div>
   		<textarea rows="5" cols="61"></textarea>
 	</div>
	<div>
   		<input type="submit" value="Submit Comment!" class="submitbutton" />
    </div>
</form>

<div>
	<div class="commentbox">
    	<img src="../images/user2.jpg" class="commentuser"/>
        <div class="nameuser">@Jieun 1 minute ago Said :</div>
        <div class="comment">Lah kok ada gue di atas?</div>
    </div>
    <div class="commentbox">
    	<img src="../images/ibuOADTSPD3HEK.gif" class="commentuser"/>
        <div class="nameuser">@Hyosung 3 minute ago Said :</div>
        <div class="comment">Yaudah gak usah bikin, gue juga udah pasrah :(</div>
    </div>
    <div class="commentbox">
    	<img src="../images/User3.gif" class="commentuser"/>
        <div class="nameuser">@Hyorin 5 minute ago Said :</div>
        <div class="comment">Hemm, tadi gue cari di internet pusing lah liatnya -_-</div>
    </div>
    <div class="commentbox">
    	<img src="../images/2507626_460s.jpg" class="commentuser"/>
        <div class="nameuser">@Dedel 13 minute ago Said :</div>
        <div class="comment">Nanya dong, cara bikin media queries gimana sih?</div>
    </div>
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
