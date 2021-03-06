package com.tmj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
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
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("loggedIn", null);
		
		mRD = request.getRequestDispatcher("index.jsp");
		mRD.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(mAction == null) {
			request.getSession().setAttribute("user", null);
			request.getSession().setAttribute("loggedIn", null);
			
			response.sendRedirect("/");
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
			
			String avatarPath = username.concat(".jpg");
			User user = new User(username, password, fullname, birthdate, email, avatarPath);
			
			user.addOnDB();
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("loggedIn", new Boolean(true));
			
			response.sendRedirect("dashboard");
		}
	}
}
