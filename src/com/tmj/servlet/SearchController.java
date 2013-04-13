package com.tmj.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tmj.model.Category;
import com.tmj.model.Tag;
import com.tmj.model.Task;
import com.tmj.model.User;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see BaseController#BaseController()
     */
    public SearchController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		if(checkLoggedIn(request, response))
			return;
		
		String query = request.getParameter("query");
		String typeQuery = request.getParameter("typeQuery");
		
		if((typeQuery.equals("user"))) {
			User[] users = User.getUserFromQuery(query);
			request.setAttribute("users", users);
		} else if(typeQuery.equals("kategori")) {
			Category[] categories = Category.getCategoryFromQuery(query);
			request.setAttribute("categories", categories);
		} else if(typeQuery.equals("tag")) {
			Task[] tasks = Tag.getAllTaskFromTagname(query);
			request.setAttribute("tasks", tasks);
		} else if(typeQuery.equals("task")) {
			Task[] tasks = Task.getTaskFromQuery(query);
			request.setAttribute("tasks", tasks);
		} else if(typeQuery.equals("all")) {
			User[] users = User.getUserFromQuery(query);
			Task[] tasks = Task.getTaskFromQueryComplete(query);
			Category[] categories = Category.getCategoryFromQuery(query);
			
			request.setAttribute("users", users);
			request.setAttribute("tasks", tasks);
			request.setAttribute("categories", categories);
		}
		
		mRD = request.getRequestDispatcher("search/index.jsp");
		mRD.forward(request, response);
	}

}
