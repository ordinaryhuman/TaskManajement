<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%
String mAction = request.getParameter("action");
if(mAction.equals("attachment")) {
	Integer id = new Integer(request.getParameter("id"));
	Attachment attachment = Attachment.getAttachmentFromAttachmentID(id);
	attachment.deleteOnDB();
}
%>