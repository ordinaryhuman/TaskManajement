<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmj.model.User" %>
<%
	User user = (User) request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>My Dashboard</title>
    <link rel='stylesheet' type="text/css" href="../style/Design.css"/>
    <script type="text/javascript" src="../script/validation.js"> </script>
    <script type="text/javascript" src="../script/global.js"> </script>
    <script type="text/javascript" src="../script/dashboard.js"> </script>
</head>

<body class="main">

<!-- HEADER -->

<jsp:include page="../pages/header.jsp"></jsp:include>
    
<!-- Content -->
<div class="kategori">
	<h3> Kategori </h3>
    <ul class="navigation2">
    <li> <a href="kategori.php" target="categoryframe" id="category-all" onclick="selectCategory(null)"> All </a></li>
    <?php
    	foreach ($categories as $category) {
			printf('<li>');
			if($category->creatorID == $_SESSION['username']) {
				printf('<a href="dashboard.php?action=delete&categoryID=%s"><img src="../images/delete.png"></img></a>', $category->id);
			}
			printf('<a href="kategori.php?categoryID=%s" target="categoryframe" id="%d" onclick="selectCategory(\'%s\')">%s</a>', $category->id, $category->id, $category->id, $category->name);
			printf('</li>');
		}
    ?>
    </ul>
    <a class="categbutton" href="addtugas.php" id="addtask_button">Add Task</a>
    <div align="right" >
    <a href="#category_form" id="register_pop">
        <input name="Button" type="button" value="Add Category" class="categbutton"/> </a>
        
        <!-- Popup add category -->
        
        <a href="#x" class="overlay" id="category_form"> </a>
        
        <div class="popup">
            <h2>Add Category</h2>
            <form action="dashboard.php" method="post">
	            <p>Please enter category name and user who can access it</p>
	            <div>
	                <label for="category">Category Name</label>
	                <input type="text" id="category" name="category" value="" />
	            </div>
	            <div>
	                <label for="name">User (Seperated with ";")</label>
	                <input type="text" id="name" name="name" value="" />
	            </div>
	            <div align="right">
	            	<input type="submit" value="Add Category" />
	     		</div>
			</form>
            <a class="close" href="#close"></a>
  		</div>
        
	</div>
</div>


<!-- Content -->
<div>
	<iframe src="kategori.php" width="605" height="340" name="categoryframe" id="categoryframe">  </iframe>
</div>


    
<!-- Footer -->
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>

</body>
</html>
