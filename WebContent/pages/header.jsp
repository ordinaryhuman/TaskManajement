<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<% User user = (User) request.getSession().getAttribute("user"); %>
<div id="header">
<img src="images/images/Header_3_ip_01.gif"/>
<div id="header-profpic">
<a id="header-avatar" href="profile?username=<%= user.getUsername() %>"><img src="upload/avatars/<%= user.getAvatarPath() %>" class="profpic"/></a>
<a href="profile?username=<%= user.getUsername() %>"><%= user.getFullname() %></a>
</div>
<div id="header-logo">
<a href="dashboard">
<img src="images/images/Header_3_ip_03.gif" />
</a>
</div>
<img src="images/images/Header_3_ip_04.gif"  />

  	<ul class="navigation">
		<li> <a href="dashboard"> Dashboard </a> </li>
        <li> <a href="profile?username=<%= user.getUsername() %>"> Profile </a> </li>
        <li> <a href="home"> Log Out </a> </li>
    </ul>
    <form method="post" action="search">
    	<input type="text" name="query" />
    	<input type="radio" name="typeQuery" value="user"/> User
    	<input type="radio" name="typeQuery" value="kategori"/> Kategori
    	<input type="radio" name="typeQuery" value="task"/> Task
    	<input type="radio" name="typeQuery" value="tag"/> Tag
    	<input type="radio" name="typeQuery" value="all" checked="checked"/> All
    	<input type="submit" value="Search" />
    </form>
</div>
