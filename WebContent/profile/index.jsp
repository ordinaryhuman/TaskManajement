<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
User user = (User) request.getAttribute("user");
String pass = user.getPassword();
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

<div class="kategori2">
	<h2> Profile </h2>
    <div><img src="upload/avatars/<%= user.getAvatarPath() %>" class="profpic"/></div>
    <form id="avatarForm" action="profile?action=uploadAvatar&username=<%= user.getUsername() %>" method="post" enctype="multipart/form-data">
	    <div id="avatarEdit" hidden>
	    		<input type="file" name="avatar" id="avatar" onchange="getAvatarPath('<%= user.getUsername() %>')"/>
	    		<input type="submit" value="Submit"/>
	    </div>
    </form>
    
</div>
<form action="profile" method="post">
<div class="contentprofile">
	<div align="right" id="editInput"> <input type="button" value="Edit Profile" class="buttonbox1" onclick="toogleEdit()"/> </div>
    <div align="right" id="submitInput" hidden>
    	<input type="button" value="Undo Edit" class="buttonbox1" onclick="toogleEdit()"/>
    	<input type="submit" value="Submit Edit" class="buttonbox1" />
   	</div>
   	<input value="edit" name="action" hidden/>
   	<input value="<%= user.getUsername() %>" name="username" hidden/>
   	
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
    	<div class="iinfo" id="oldpass_info"></div>
    	<p>Password Lama : <br>
    	<% 
    		out.println(String.format("<input type='password' id='oldpass' name='oldpassword'  onChange='validate_oldpass(\"%s\")'/></p>", user.getPassword()));
    	%>
	    <div class="iinfo" id="pass_info"></div>
	    <p>Password Baru : <br>
	    <input type="password" id="passid" name="newpassword" onChange="validate_passid()"/></p>
	    <div class="iinfo" id="confpass_info"></div>
	    <p>Konfirmasi Password : <br>
	    <input type="password" id="confirmpass" name="confirmnewpassword" onChange="validate_confirmpass()"/></p>
    </div>
      <hr noshade="noshade" />
      <p class="paragraph2">Task DONE :</p>
      <ul class="paragraph2">
      <%
      	for(Task task : tasksDone) {
      		out.println(String.format("<li><a href='task?taskid=%d'>", task.getID()));
      		out.println(task.getTaskname());
      		out.println("</a></li>");
      	}
      %>
        </ul>
	<p class="paragraph2">Task NOT-DONE :</p>
      <ul class="paragraph2">
        <%
      	for(Task task : tasksNotDone) {
      		out.println(String.format("<li><a href='task?taskid=%d'>", task.getID()));
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
