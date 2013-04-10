<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
User user = (User) request.getAttribute("user");
Task[] tasksDone = (Task[]) request.getAttribute("tasksDone");
Task[] tasksNotDone = (Task[]) request.getAttribute("tasksNotDone");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>My Profile</title>
	<link rel='stylesheet' type="text/css" href="style/Design.css"/>
    <script type="text/javascript" src="script/validation.js"> </script>
    <script type="text/javascript" src="script/global.js"> </script>
    <script type="text/javascript" src="script/profile.js"> </script>
</head>
</head>

<body class="main">

<!-- Header -->
<jsp:include page="../pages/header.jsp"></jsp:include>

<!-- Content -->
<form action="profile" method="post">
<div class="kategori2">
	<h2> Profile </h2>
    <div><img src="upload/avatars/<%= user.getAvatarPath() %>" class="profpic"/></div>
    <div id="avatarEdit" hidden><input type="file" name="avatar"/></div>
    <div align="right" id="editInput"> <input type="button" value="Edit Profile" class="buttonbox1" onclick="toogleEdit()"/> </div>
    <div align="right" id="submitInput" hidden>
    	<input type="button" value="Undo Edit" class="buttonbox1" onclick="toogleEdit()"/>
    	<input type="submit" value="Submit Edit" class="buttonbox1" />
   	</div>
   	<input value="edit" name="action" hidden/>
   	<input value="<%= user.getUsername() %>" name="username" hidden/>
</div>
    
<div class="contentprofile">
	
	<p>Username	: <br>
	<div id="usernameDisp"><%= user.getUsername() %></div>
	</p>
    <p>Nama Lengkap	: <br>
    <div id="fullnameDisp"><%= user.getFullname() %></div>
    <div id="fullnameEdit" hidden><input type="text" name="fullname" value="<%= user.getFullname() %>" /></div>
    </p>
	<p>Tanggal lahir : <br>
	<div id="birthdateDisp"><%= user.getBirthdate() %></div>
	<div id="birthdateEdit" hidden><input id="dateInput" type="date" name="birthdate" value="<%= user.getBirthdate() %>" /></div>
	</p>
    <p>Email : <br>
    <div id="emailDisp"><%= user.getEmail() %></div>
    <div id="emailEdit" hidden><input type="text" name="email" value="<%= user.getEmail() %>" /></div>
    </p>
    <div id="password" hidden>
    	<p>Password Lama : <br>
	    <input type="password" name="oldpassword" /></p>
	    <p>Password Baru : <br>
	    <input type="password" name="newpassword" /></p>
	    <p>Konfirmasi Password : <br>
	    <input type="password" name="confirmnewpassword" /></p>
    </div>
      <hr noshade="noshade" />
      <p class="paragraph2">Task DONE :</p>
      <ul class="paragraph2">
      <%
      	for(Task task : tasksDone) {
      		out.println(String.format("<li><a href='task?taskID=%d'>", task.getID()));
      		out.println(task.getTaskname());
      		out.println("</a></li>");
      	}
      %>
        </ul>
	<p class="paragraph2">Task NOT-DONE :</p>
      <ul class="paragraph2">
        <%
      	for(Task task : tasksNotDone) {
      		out.println(String.format("<li><a href='task?taskID=%d'>", task.getID()));
      		out.println(task.getTaskname());
      		out.println("</a></li>");
      	}
      	%>
      </ul>


</div>
</form>

        

<!-- FOoter -->
    
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>

</body>
</html>
