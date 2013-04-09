package com.tmj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmj.model.User;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4897804423534143092L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(mAction == null) {
			request.getSession().setAttribute("user", null);
			request.getSession().setAttribute("loggedIn", null);
			
			response.sendRedirect("index.jsp");
		} else if(mAction.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User(username, password);
			int retval = user.authenticate();
			if(retval == 0) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("loggedIn", new Boolean(true));
				
				response.sendRedirect("dashboard");
			} else {
				request.getSession().setAttribute("loggedIn", false);
				
				response.sendRedirect(String.format("index.jsp?status=%d", retval));
			}
		} else if(mAction.equals("register")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String birthdate = request.getParameter("birthdate");
			String email = request.getParameter("email");
			
			// upload file
			
			String avatarPath = request.getParameter("avatar_path");
			User user = new User(username, password, fullname, birthdate, email, avatarPath);
			
			user.addOnDB();
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("loggedIn", new Boolean(true));
			
			response.sendRedirect("dashboard");
		} else if(mAction.equals("registrationEmailAJAX")) {
			ServletOutputStream out = response.getOutputStream();
			
			String email = request.getParameter("email");
			User user = User.getUserFromEmail(email);
			if(user == null) {
				out.println(1);
			} else {
				out.println(0);
			}
		} else if(mAction.equals("registrationUsernameAJAX")) {
			ServletOutputStream out = response.getOutputStream();
			
			String username = request.getParameter("username");
			User user = User.getUserFromUsername(username);
			out.println(username);
			if(user == null) {
				out.println(1);
			} else {
				out.println(0);
			}
		}
	}
}
