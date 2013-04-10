package com.tmj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tmj.model.Category;
import com.tmj.model.Task;
import com.tmj.model.User;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/dashboard")
public class DashboardController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		if(mAction == null) {
			mRD = request.getRequestDispatcher("dashboard/index.jsp");
			mRD.forward(request, response);
		} else if(mAction.equals("delete")) {
			Integer categoryID = new Integer(request.getParameter("categoryID"));
			Category.getCategoryFromCategoryID(categoryID).deleteOnDB();
			
			mRD = request.getRequestDispatcher("dashboard/index.jsp");
			mRD.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		checkLoggedIn(request, response);
		
		User activeUser = (User) request.getSession().getAttribute("user");
		
		if(mAction.equals("addCategory")) {
			String categoryName = request.getParameter("categoryname");
			String usernameMembers = request.getParameter("users").concat(";" + activeUser.getUsername());
			
			Category category = new Category(Category.getAvailableCategoryID(), categoryName, activeUser.getUsername());
			category.addOnDB();
			category.setMembers(usernameMembers);
			
			mRD = request.getRequestDispatcher("dashboard/index.jsp");
			mRD.forward(request, response);
		}
	}

}
