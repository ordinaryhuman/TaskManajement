package com.tmj.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.nio.file.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.*;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.tmj.model.Attachment;
import com.tmj.model.Category;
import com.tmj.model.Comment;
import com.tmj.model.Tag;
import com.tmj.model.Task;
import com.tmj.model.User;

/**
 * Servlet implementation class TaskController
 */
@WebServlet("/task")
public class TaskController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseController#BaseController()
     */
    public TaskController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		if(mAction == null) {
			Integer taskID = new Integer(request.getParameter("taskid"));
			Task task = Task.getTaskFromTaskID(taskID);
			Attachment[] attachments = task.getAttachments();
			User[] assignees = task.getAssignees();
			Tag[] tags = task.getTags();
			Comment[] comments = task.getComments();
			
			request.setAttribute("task", task);
			request.setAttribute("tags", tags);
			request.setAttribute("comments", comments);
			request.setAttribute("attachments", attachments);
			request.setAttribute("assignees", assignees);
			
			mRD = request.getRequestDispatcher("task/view.jsp");
			mRD.forward(request, response);
		} else if(mAction.equals("add")) {
			Integer categoryID = new Integer(request.getParameter("categoryID"));
			Category category = Category.getCategoryFromCategoryID(categoryID);
			
			request.setAttribute("category", category);
			
			mRD = request.getRequestDispatcher("task/add.jsp");
			mRD.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		if(mAction.equals("Delete Task")) {
			Task task = Task.getTaskFromTaskID(new Integer(request.getParameter("taskID")));
			User user = (User) request.getSession().getAttribute("user");
			
			if(task.getUsername().equals(user.getUsername())) {
				// user is owner
				// so delete task completely
				task.deleteOnDB();
			} else {
				// user is assignee
				// so remove him from assignee
				task.deleteAssignee(user.getUsername());
			}
			
			//redirect to dashboard
			response.sendRedirect("dashboard");
			
		} else if(mAction.equals("addTask")) {
			Integer taskID = Task.getAvailableTaskID();
			
			response.sendRedirect(String.format("task?taskid=%d", taskID));
		} else if(mAction.equals("addAttachment")) {
			Integer taskID = new Integer(request.getParameter("taskID"));
			Integer id = Attachment.getAvailableAttachmentID();
			String filename = String.format("att_%d_%s", taskID, request.getParameter("file-name"));
			String type = request.getParameter("type");
			request.getParameter("rincian-attachment-path");
			String filepath = String.format("%s/upload/attachments/%s", request.getRealPath("/"), filename);
			
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			((DiskFileItemFactory) factory).setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    InputStream input = item.getInputStream();
				    FileOutputStream output = new FileOutputStream(filepath);
				    
				    int tmp;
				    while ((tmp = input.read()) != -1 ) {
				    	output.write(tmp);
				    }
				    
				    output.close();
				    input.close();
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			Attachment attachment = new Attachment(id, taskID, filename, type);
			attachment.addOnDB();
			
			response.sendRedirect(String.format("task?taskid=%d", taskID));
		}
	}

}
