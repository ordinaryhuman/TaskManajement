/**
 * @author gmochid
 */
window.onload = function() {
	window.selectedCategory = $id('category-all');
	selectCategoryAJAX(0);
	$id("addtask_button").style.visibility = 'hidden';
}

function changeTaskStateAJAX(taskID) {
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
			$id('task_status' + taskID).innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/changeTaskStatus.jsp?taskID=" + taskID, true);
	xmlhttp.send();
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
			
			var s = '<h4 align="center">' + response[0].name + '</h4>';
			s = s + 'Click on task name to see the details';
			s = s + '<table width="580" border="1" cellspacing="0" cellpadding="0">';
			
			i = 1;
			lim = ((response.length - 1) % 2 == 0) ? response.length - 1 : response.length - 2;
			for(; i <= (lim / 2); i++) {
				s = s + '<tr>';
				for(j = 0; j < 2; j++) {
					obj = response[i * 2 + j - 1];
					s = s + '<td width="26" class="black"><p class="kategori_status" id="task_status' + obj.ID + '">' + obj.status + '</p>' +
						'<a class="kategori_statuschange" onclick="changeTaskStateAJAX(' + obj.ID + ')">(change)</a></td>';
					s = s + '<td width="264" class="' + ((i + j - 1) % 2 == 0 ? 'blue' : 'green') + '">' + 
						'<a href="rincian?taskid=' + obj.ID + '" target="_parent" class="ordintext">' + obj.taskname + '</a><br />';
					s = s + 'Deadline : <b class="redtext">' + obj.deadline + '</b><br />';
					s = s + 'Kategori : ' + obj.category + '<br />';
					s = s + obj.tags;
				}
				s = s + '</tr>';
			}
			if((response.length - 1) % 2 != 0) {
				obj = response[i * 2 - 1];
				s = s + '<tr>';
				s = s + '<td width="26" class="black"><p class="kategori_status" id="task_status' + obj.ID + '">' + obj.status + '</p>' +
					'<a class="kategori_statuschange" onclick="changeTaskStateAJAX(' + obj.ID + ')">(change)</a></td>';
				s = s + '<td width="264" class="' + ((i + j - 1) % 2 == 0 ? 'blue' : 'green') + '">' + 
					'<a href="rincian?taskid=' + obj.ID + '" target="_parent" class="ordintext">' + obj.taskname + '</a><br />';
				s = s + 'Deadline : <b class="redtext">' + obj.deadline + '</b><br />';
				s = s + 'Kategori : ' + obj.category + '<br />';
				s = s + obj.tags;
				s = s + '</tr>';
			}
			
			s = s + '</table>';
			
			$id('content').innerHTML = s;
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/addCategoryDashboard.jsp?categoryID=" + selectedCategoryID, true);
	xmlhttp.send();
}
