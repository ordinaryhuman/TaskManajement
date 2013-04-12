<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<%@ page import="com.tmj.model.Category" %>
<%
	User user = (User) request.getSession().getAttribute("user");
	Category[] categories = Category.getAllCategory();
%>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>My Dashboard</title>
    <link rel='stylesheet' type="text/css" href="style/Design.css"/>
    <script type="text/javascript" src="script/validation.js"> </script>
    <script type="text/javascript" src="script/global.js"> </script>
    <script type="text/javascript" src="script/dashboard.js"> </script>
</head>

<body class="main">

<!-- HEADER -->

<jsp:include page="../pages/header.jsp"></jsp:include>
    
<!-- Content -->
<div class="kategori">
	<h3> Kategori </h3>
    <ul class="navigation2">
    <li> <a target="categoryframe" id="category-all" onclick="selectCategoryAJAX(0)"> All </a></li>
    <%
    if(categories != null)
    for(Category category: categories) {
    	out.println("<li>");
    	if(category.getCreatorID().equals(user.getUsername())) {
    		out.println(String.format("<a href='dashboard?action=delete&categoryID=%d'><img src='images/delete.png'></img></a>",
    				category.getID()));
    	}
    	out.println(String.format("<a target='categoryframe' id='%d' onclick='selectCategoryAJAX(%d)'>%s</a>",
    			category.getID(), category.getID(), category.getName()));
    	out.println("</li>");
    }
    %>
    </ul>
    <a class="categbutton" href="addtugas.php" id="addtask_button">Add Task</a>
    <div align="right" >
    <a href="#category_form" id="register_pop">
        <input name="Button" type="button" value="Add Category" class="categbutton"/> </a>
        
        <!-- Popup add category -->
        
        <a href="#x" class="overlay" id="category_form"> </a>
        
        <div class="popup">
            <h2>Add Category</h2>
            <form action="dashboard" method="post">
	            <p>Please enter category name and user who can access it</p>
	            <div>
	                <label for="category">Category Name</label>
	                <input type="text" id="category" name="categoryname" value="" />
	            </div>
	            <div>
	                <label for="name">User (Seperated with ";")</label>
	                <input type="text" id="name" name="users"/>
	            </div>
	            <div align="right">
	            	<input name="action" value="addCategory" hidden="true"/>
	            	<input type="submit" value="Add Category" />
	     		</div>
			</form>
            <a class="close" href="#close"></a>
  		</div>
        
	</div>
</div>


<!-- Content -->
<div id="content">
	
</div>


    
<!-- Footer -->
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>
<a id="activeUser" hidden><%= user.getUsername() %></a>

</body>
</html>
