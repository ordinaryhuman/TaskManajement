<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<head>
	<title>TaskManagement</title>
	<link rel='stylesheet' type="text/css" href="style/Design.css"/>
    <script type="text/javascript" src="script/validation.js"> </script>
    <script type="text/javascript" src="script/calendar.js"></script>
    <script type="text/javascript" src="script/home.js"></script>
</head>

<body class="main">
	<!-- Header -->
<div>

<img src="images/Header_4_ip.gif"></div>
    
    
	<!-- Content -->
<div class="LoginBoard">
   
    
<div>
    	<div class="panel">
          	<a href="#login_form" id="register_pop">Log In</a>
        	<a href="#register_form" id="register_pop">Register</a>
    	</div>

	</div>
    
    <div class="paragraph">
    	<p class="judul">WELCOME to TaskManajement!</p>
    	<p>TaskManajement is a web based application to manage your Task. </p>
    	<p>You can also share your Task with your friends.</p>
    	<p>Like our motto, Prodictiveness is the key to Success, So <b>LET'S STAY PRODUCTIVE</b> with <b>TaskManajement.</b></p>
    	<p>Click <b>REGISTER</b> if you wants to join us or <b>LOGIN</b> if you already join.</p>
        </div>
    
<!-- Popup Login -->
    <a href="#x" class="overlay" id="login_form"> </a>
        <div class="popup">
        	<form action="HomeController" method="post">
            <h2>User Login</h2>
            <p>Please enter Username and Password</p>
            <div>
                <label for="userlogin">Username</label>
                <input type="text" id="userlogin" name="username" value="" />
            </div>
            <div>
                <label for="passlogin">Password</label>
                <input type="password" id="passlogin" name="password" value="" />
            </div>
            <div align="right">
            	<input name="action" value="login" hidden="true"/>
            	<input type="submit" value="Login"/>
            </div>

            <a class="close" href="#close"></a>
            </form>
  		</div>
    
    <!-- Popup Register -->
    <a href="#x" class="overlay" id="register_form"> </a>
        <div class="popup">
        <form action="HomeController" method="post">
            <h2>Register</h2>
            <p>Please enter your details here</p>
            
            <div class="iinfo" id="name_info"></div>
            <div>
                <label for="userid">Username</label>
                <input type="text" id="userid" value="" name="username" onKeyUp="validate_userid();validate_subreg();validateUsernameAJAX();"/>
                <div class="requirement">
                Min. 5 characters, only space and alphabet.<br>
                <a id="userVal" class="ajaxAlert"></a>
                </div>
            </div>
            
            <div class="iinfo" id="pass_info"></div>
            <div>
                <label for="passid">Password</label>
                <input type="password" id="passid" name="password" value="" onKeyUp="validate_passid()" onChange="validate_subreg()"/>
                <div class="requirement">Min. 8 characters.</div>
            </div>
            
            <div class="iinfo" id="confpass_info"></div>
            <div>
                <label for="confirmpass">Confirm Password</label>
                <input type="password" id="confirmpass" value="" onKeyUp="validate_confirmpass()" onChange="validate_subreg()"/>
                <div class="requirement">Must be equal with password</div>
            </div>
            
            <div class="iinfo" id="fullname_info"></div>
            <div>
                <label for="fullname">Full Name</label>
                <input type="text" id="fullname" value="" name="fullname" onKeyUp="validate_fullname()" onChange="validate_subreg()"/>
                <div class="requirement">Must have space between two character.</div>
            </div>
            
            <div class="iinfo" id="birth_info"></div>
            <div>
                <label for="birth">Birth Date</label>
              <input type="text" id="birth" value="" name="birthdate" class="birthcal" onchange="validate_birth()"/>
              <input type="button" class="cal" onClick="openCal()" value="calendar"/>
                
                <!-- CALENDAR -->
						<div id="floatcal">
							<script type="text/javascript">
							//<![CDATA[
								var cal = new Calendar(
									new Date(), 
									document.getElementById("floatcal"),
									function(date){
										var yyyy = "" + date.getFullYear();
										while(yyyy.length < 4) yyyy = "0" + yyyy;
										var mm = "" + (date.getMonth() + 1); 
										while(mm.length < 2) mm = "0" + mm;
										var dd = "" + date.getDate();
										while(dd.length < 2) dd = "0" + dd;
										document.getElementById('birth').value = yyyy + "-" + mm + "-" + dd;
										openCal();
										validate_birth();
									}
								);
								
								function openCal() {
									if(cal.isVisible) cal.hideCalendar();
									else cal.drawCalendar();
								}
							//]]>
							</script>
						</div>
                
                
                <div class="requirement">YYYY-MM-DD</div>
      </div>
            
	            <div class="iinfo" id="email_info"></div>
	            <div>
	                <label for="email">Email</label>
	                <input name="email" type="text" id="email" value="" onKeyUp="validate_email();validate_subreg();validateEmailAJAX();"/>
	                <div class="requirement">
	                Min. 1 character before @.<br/>Min. 1 character between @ and'.'<br/>Top level domain should contain at least 2 characters.<br/>ex. x@x.xx<br>
	                <a id="emailVal" class="ajaxAlert"></a>
	                </div>
	            </div>
	            
	             <div class="iinfo" id="avatar_info"></div>
	            <div>
	                <label for="avatar">Avatar</label>
	                <input type="file" name="avatar_path" id="avatar" value="" onKeyUp="validate_avatar()" onChange="validate_subreg()"/>
	                <div class="requirement">File must be .jpg or .jpeg.</div>
	            </div>
	            <input name="action" value="register" hidden="true"/>
	       		<div align="right"><input id="subreg" type="submit" value="Register" name="register">
	       		<script>document.getElementById("subreg").disabled=true;</script>
	            </div>
     
            

            <a class="close" href="#close"></a>
            </form>
		</div>
    
    
</div>
    
<!-- Footer -->
<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>
<script type="text/javascript">
window.onload = function() {
<%
if(request.getParameter("status") != null) {
	String s = request.getParameter("status");
	if(s == "1") {
		out.println("alert('User not found');");
	} else {
		out.println("alert('Password mismatch');");
	}
}
%>
}
</script>

</body>