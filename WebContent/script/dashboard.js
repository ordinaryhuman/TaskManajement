/**
 * @author gmochid
 */
window.onload = function() {
	window.selectedCategory = $id('category-all');
	selectCategoryAJAX(0);
	$id("addtask_button").style.visibility = 'hidden';
}

function selectCategoryAJAX(selectedCategoryID) {
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
			
			for(i = 1; i < response.length; i++) {
				console.log(response[i]);
			}
			
			$id('content').innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/addCategoryDashboard.jsp?categoryID=" + selectedCategoryID, true);
	xmlhttp.send();
}
