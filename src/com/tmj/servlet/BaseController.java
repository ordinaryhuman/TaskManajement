package com.tmj.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmj.model.User;

/**
 * Servlet implementation class BaseController
 */
@WebServlet("/BaseController")
public class BaseController extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 5508341657944436572L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BaseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mAction	= request.getParameter("action");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mAction	= request.getParameter("action");
	}
	
	protected void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	protected void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
	
	protected Boolean checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			mRD = request.getRequestDispatcher("index.jsp");
			mRD.forward(request, response);
			return true;
		}
		return false;
	}

	protected RequestDispatcher mRD;
	protected String mAction;
}
