<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.*" %>
<%@ page import="org.json.*" %>
<% 
	Integer categoryID = new Integer(request.getParameter("categoryID"));
	Task[] tasks = (Task[]) ((categoryID == 0) ? Task.getAllTask() : Task.getTasksFromCategoryID(categoryID));
	
	JSONArray retval = new JSONArray();
	
	try {
		JSONObject o = new JSONObject();
		if(categoryID == 0) {
			o.append("ID", categoryID);
			o.append("name", "ALL");
		} else {
			Category category = Category.getCategoryFromCategoryID(categoryID);
			o.append("ID", category.getID());
			o.append("name", category.getName());
		}
		
		retval.put(0, o);
		
		for(int i = 1; i <= tasks.length; i++) {
			JSONObject obj = new JSONObject();
			
			obj.append("ID", tasks[i-1].getID());
			obj.append("category", tasks[i-1].getCategory().getName());
			obj.append("username", tasks[i-1].getUsername());
			obj.append("taskname", tasks[i-1].getTaskname());
			obj.append("status", tasks[i-1].getStatus() ? "DONE" : "NOT-DONE");
			obj.append("deadline", tasks[i-1].getDeadline());
			obj.append("tags", "tasks[i-1].getTagsString()");
			
			retval.put(i, obj);
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
	
	out.println(retval.toString());
%>