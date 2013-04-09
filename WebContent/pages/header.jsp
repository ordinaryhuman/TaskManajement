<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<% User user = (User) request.getSession().getAttribute("user"); %>
<div id="header">
<img src="../images/images/Header_3_ip_01.gif"/><img src="../images/images/Header_3_ip_02.gif" /><a href="Dashboard.html"><img src="../images/images/Header_3_ip_03.gif" /></a><img src="../images/images/Header_3_ip_04.gif"  />

  	<ul class="navigation">
		<li> <a href="/dashboard"> Dashboard </a> </li>
        <li> <a href="/profil?username=<%= user.getUsername() %>"> Profile </a> </li>
        <li> <a href="/home"> Log Out </a> </li>
    </ul>
    <form method="post" action="search">
    	<input type="text" name="query" />
    	<input type="submit" value="Search" />
    </form>
</div>
