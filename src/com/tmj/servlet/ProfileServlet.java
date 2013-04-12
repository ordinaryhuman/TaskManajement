package com.tmj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		} else if(mAction.equals("uploadAvatar")) {
			
		}
		response.sendRedirect("profile");
	}

}
