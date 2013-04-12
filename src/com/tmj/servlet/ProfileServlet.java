package com.tmj.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.tmj.model.Attachment;
import com.tmj.model.Task;
import com.tmj.model.User;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		User user = User.getUserFromUsername(request.getParameter("username"));
		Task[] taskDone = Task.getTasksDoneFromUsername(user.getUsername());
		Task[] taskNotDone = Task.getTasksNotDoneFromUsername(user.getUsername());
		
		request.setAttribute("user", user);
		request.setAttribute("tasksDone", taskDone);
		request.setAttribute("tasksNotDone", taskNotDone);
		
		mRD = request.getRequestDispatcher("profile/index.jsp");
		mRD.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		if(mAction.equals("edit")) {
			User user = User.getUserFromUsername(request.getParameter("username"));
			user.setBirthdate(request.getParameter("birthdate"));
			user.setEmail(request.getParameter("email"));
			user.setFullname(request.getParameter("fullname"));
			if(user.getPassword().equals(request.getParameter("oldpassword"))) {
				if(request.getParameter("newpassword").equals(request.getParameter("confirmnewpassword"))) {
					user.setPassword(request.getParameter("newpassword"));
				}
			}
			user.editOnDB();
			
			response.sendRedirect(String.format("profile?username=%s", user.getUsername()));
		} else if(mAction.equals("uploadAvatar")) {
			User user = User.getUserFromUsername(request.getParameter("username"));
			String filename = String.format("%s.%s", user.getUsername(), request.getParameter("filename"));
			request.getParameter("avatar");
			String filepath = String.format("%s/upload/avatars/%s", request.getRealPath("/"), filename);
			
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
			
			response.sendRedirect(String.format("profile?username=%s", user.getUsername()));
		}
		
	}

}
