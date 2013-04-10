<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%@ page import="org.json.*" %>
<%
	Integer taskID = new Integer(request.getParameter("taskID"));
	Task task = Task.getTaskFromTaskID(taskID);
	task.setStatus(!task.getStatus());
	task.editOnDB();
	out.println(task.getStatus() ? "DONE" : "NOT-DONE");
%>