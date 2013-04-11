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
	
	edits = document.getElementsByClassName('edit');
	for(i = 0; i < edits.length; i++) {
		edit = edits[i];
		edit.hidden = !edit.hidden;
	}
	
	if($id('rincianbutton-edit') == 'Edit Task') {
		$id('rincianbutton-edit').value = 'End Edit';
	} else {
		$id('rincianbutton-edit').value = 'Edit Task';
	}
}

function editDeadline(id) {
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
	
	deadline = $id('rincianinput-deadline').value;
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=editDeadline&taskID=" + id + "&deadline=" + deadline,true);
	xmlhttp.send();
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
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=deleteAttachment&id=" + id,true);
	xmlhttp.send();
}

function deleteTag(tagID, taskID) {
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
			deleteChild($id('rincian-tag').childNodes[1], $id('rincian-tag-' + tagID));
		}
	}
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=deleteTag&tagID=" + tagID + "&tagID=" + taskID,true);
	xmlhttp.send();
}

function deleteAssignee(username, taskID) {
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
			deleteChild($id('rincian-assignee').childNodes[1], $id('rincian-assignee-' + username));
		}
	}
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=deleteAssignee&tagID=" + username + "&tagID=" + taskID,true);
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

function sendStatus(taskid) {
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
	
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=editStatus&taskID=" + taskid,true);
	xmlhttp.send();
}