<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
User user = (User) request.getAttribute("user");
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
    <div><img src="../images/ibuOADTSPD3HEK.gif" class="profpic"/></div>
    <p><div align="right"> <input type="button" value="Edit Profile" class="buttonbox1" onclick="toogleEdit()"/> </div></p>
</div>
    
<div class="contentprofile">
	
	<p>Username	: <br>
	<div id="usernameDisp"><%= user.getUsername() %></div>
	<div id="usernameEdit"hidden><input type="text" name="username" /></div>
	</p>
    <p>Nama Lengkap	: <br>
    <div id="fullnameDisp"><%= user.getFullname() %></div>
    <div id="fullnameEdit" hidden><input type="text" name="fullname" /></div>
    </p>
	<p>Tanggal lahir : <br>
	<div id="birthdateDisp"><%= user.getBirthdate() %></div>
	<div id="birthdateEdit" hidden><input type="date" name="birthdate" /></div>
	</p>
    <p>Email : <br>
    <div id="emailDisp"><%= user.getEmail() %></div>
    <div id="emailEdit" hidden><input type="text" name="email" /></div>
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
      <p class="paragraph2">Task :</p>
      <ul class="paragraph2">
        <li>Tubes Progin</li>
        <li>Tubes AI</li>
        <li>Tugas Individu IMK 2</li>
        <li>Tugas Mingguan KAP</li>
        <li>Tugas Kecil Main-main</li>
        <li>Tugas Kecil Main-main</li>
        </ul>
	<p class="paragraph2">Done :</p>
      <ul class="paragraph2">
        <li>Tugas Kecil AI</li>
        <li>Tugas Individu IMK</li>
        <li>Proposal IMK</li>
        <li>Tubes OOP</li>
      </ul>


</div>


        

<!-- FOoter -->
    
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>

</body>
</html>
