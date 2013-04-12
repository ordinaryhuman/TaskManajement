<%@page import="sun.org.mozilla.javascript.internal.JavaAdapter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%@ page import="org.json.*" %>
<%@ page import="java.nio.file.*" %>
<%@ page import="java.io.*" %>
<%
String mAction = request.getParameter("action");
if(mAction.equals("deleteAttachment")) {
	Integer id = new Integer(request.getParameter("id"));
	Attachment attachment = Attachment.getAttachmentFromAttachmentID(id);
	attachment.deleteOnDB();
} else if(mAction.equals("deleteTag")) {
	Integer taskID = new Integer(request.getParameter("taskID"));
	Tag tag = new Tag(taskID, request.getParameter("tagname"));
	tag.deleteOnDB();
} else if(mAction.equals("deleteAssignee")) {
	String username = request.getParameter("username");
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	task.deleteAssignee(username);
} else if(mAction.equals("editDeadline")) {
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	String deadline = request.getParameter("deadline");
	task.setDeadline(deadline);
	task.editOnDB();
} else if(mAction.equals("editStatus")) {
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	task.setStatus(!task.getStatus());
	task.editOnDB();
} else if(mAction.equals("tagHint")) {
	
} else if(mAction.equals("assigneeHint")) {
	
} else if(mAction.equals("addTag")) {
	Integer taskID = new Integer(request.getParameter("taskID"));
	String tagname = request.getParameter("tagname");
	Tag tag = new Tag(taskID, tagname);
	tag.addOnDB();
} else if(mAction.equals("addAssignee")) {
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	String username = request.getParameter("username");
	task.addAssignee(username);
	out.println(User.getUserFromUsername(username).getFullname());
} else if(mAction.equals("addComment")) {
	User user = (User) request.getSession().getAttribute("user");
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	String content = request.getParameter("content");
	
	Integer commentID = Comment.getAvailableCommentID();
	new Comment(commentID, task.getID(), user.getUsername(), content).addOnDB();
	
	Comment comment = Comment.getCommentFromCommentID(commentID); 
} else if(mAction.equals("getCommentPages")) {
	Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
	Integer pages = new Integer(request.getParameter("pages"));
	User user = (User) request.getSession().getAttribute("user");
	
	Comment[] comments = Comment.getCommentFromTaskID(task.getID());
	
	JSONArray arr = new JSONArray();
	int j = 0;
	for(int i = pages * 10; i < (pages * 10 + 10); i++) {
		if(i < comments.length) {
			JSONObject obj = new JSONObject();
			obj.append("id", comments[i].getID());
			obj.append("content", comments[i].getContent());
			obj.append("username", comments[i].getUsername());
			obj.append("timestamp", comments[i].getTimestamp());
			obj.append("avatarPath", user.getAvatarPath());
			
			arr.put(j++, obj);
		}
	}
	
	out.println(arr.toString());
} else if(mAction.equals("addAttachment")) {
	Integer taskID = new Integer(request.getParameter("taskID"));
	Integer id = Attachment.getAvailableAttachmentID();
	String type = request.getParameter("type");
	
	String sourcePath = request.getParameter("sourcePath");
	String filename = request.getParameter("sourceFilename");
	String destPath = String.format("upload\\attachments\\att_%d_%s", taskID, filename);
	
	Path source = Paths.get(sourcePath);
	Path dest = Paths.get(destPath);
	
	Files.copy(source, dest);
	
	Attachment attachment = new Attachment(id, taskID, filename, type);
	attachment.addOnDB();
	
	JSONObject obj = new JSONObject();
	obj.append("id", id);
	obj.append("destPath", destPath);
	out.println(obj.toString());
}
%>