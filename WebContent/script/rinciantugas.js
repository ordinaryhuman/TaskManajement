/**
 * @author gmochid
 */
window.onload = function() {
	//tagHints();
	//assigneeHints();
}

function edittask() {
	deletes = document.getElementsByClassName('delete');
	for(i = 0; i < deletes.length; i++) {
		del = deletes[i];
		del.hidden = !del.hidden;
	}
}

function deleteAttachment(id) {
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
			deleteChild($id('rincian-attachment'), $id('rincian-attachment-' + id));
		}
	}
	xmlhttp.open("GET","ajaxHandler/deleteDetailTask.jsp?action=attachment&id=" + id,true);
	xmlhttp.send();
}

function tagHints() {
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
			deleteAllChildElements('hintlist-tag');
			response = JSON.parse(xmlhttp.responseText);
			
			for(i = 0; i < response.length; i++) {
				x = document.createElement('option');
				x.value = response[i];
				$id('hintlist-tag').appendChild(x);
			}
		}
	}
	str = $id('rincianinput-tag');
	xmlhttp.open("GET","ajaxHandler/tagHints?str=" + str,true);
	xmlhttp.send();
}

function assigneeHints() {
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
			deleteAllChildElements('hintlist-assignee');
			response = JSON.parse(xmlhttp.responseText);
			
			for(i = 0; i < response.length; i++) {
				x = document.createElement('option');
				x.value = response[i];
				$id('hintlist-assignee').appendChild(x);
			}
		}
	}
	str = $id('rincianinput-assignee');
	taskid = $id('rinciantugas-taskid').innerHTML;
	xmlhttp.open("GET","rinciantugasAJAX.php?action=assigneeHint&taskid=" + taskid,true);
	xmlhttp.send();
}

function sendStatus() {
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
		}
	}
	
	taskid = $id('rinciantugas-taskid').innerHTML;
	xmlhttp.open("GET","rinciantugasAJAX.php?action=status&taskid=" + taskid,true);
	xmlhttp.send();
}