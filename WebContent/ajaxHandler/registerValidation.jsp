<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<%
	if(request.getParameter("action").equals("validateUsernameAJAX")) {
		String username = request.getParameter("username");
		User user = User.getUserFromUsername(username);
		if(user == null) {
			out.println(1);
		} else {
			out.println(0);
		}
	} else if (request.getParameter("action").equals("validateEmailAJAX")) {
		String email = request.getParameter("email");
		User user = User.getUserFromEmail(email);
		if(user == null) {
			out.println(1);
		} else {
			out.println(0);
		}
	}
%>