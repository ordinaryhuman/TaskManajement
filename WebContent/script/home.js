function validateUsernameAJAX() {
	var xmlhttp;
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			response = JSON.parse(xmlhttp.responseText);
			
			if(response == "1") {
				document.getElementById("userVal").innerHTML = "Username Available";
			} else {
				document.getElementById("userVal").innerHTML = "Username Unavailable";
			}
		}
	}
	
	var username = document.getElementById("userid").value;
	xmlhttp.open("GET","ajaxHandler/registerValidation.jsp?action=validateUsernameAJAX&username=" + username, true);
	xmlhttp.send();
}

function validateEmailAJAX() {
	var xmlhttp;
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			response = JSON.parse(xmlhttp.responseText);
			
			if(response == "1") {
				document.getElementById("emailVal").innerHTML = "Email Available";
			} else {
				document.getElementById("emailVal").innerHTML = "Email Unavailable";
			}
		}
	}
	
	var email = document.getElementById("email").value;
	xmlhttp.open("GET","ajaxHandler/registerValidation.jsp?action=validateEmailAJAX&email=" + email, true);
	xmlhttp.send();
}
