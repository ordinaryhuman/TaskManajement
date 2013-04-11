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
	
	if($id('rincianbutton-edit').value == 'Edit Task') {
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

function deleteTag(tagname, taskID) {
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
			console.log($id("rincian-tag-" + tagname));
			deleteChild($id('rincian-tag').childNodes[1], $id('rincian-tag-' + tagname));
		}
	}
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=deleteTag&tagname=" + tagname + "&taskID=" + taskID,true);
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
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=deleteAssignee&username=" + username + "&taskID=" + taskID,true);
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

function addTag(taskid) {
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
			tagname = $id('rincian-tag-edit-tagname').value;
			
			newTag = document.createElement("li");
			newTag.id = "rincian-tag-" + tagname;
			
			s = tagname;
			s = s + "<input type='button' class='delete' value='Delete' onclick='deleteTag(" + tagname + "," + taskid + ")'/>";
			newTag.innerHTML = s;
			
			$id('rincian-tag').childNodes[1].appendChild(newTag);
			
			$id('rincian-tag-edit-tagname').value = "";
		}
	}
	
	tagname = $id('rincian-tag-edit-tagname').value;
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=addTag&taskID=" + taskid + "&tagname=" + tagname,true);
	xmlhttp.send();
}

function addAssignee(taskid) {
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
			username = $id('rincian-assignee-edit-username').value;
			
			newTag = document.createElement("li");
			newTag.id = "rincian-assignee-" + username;
			
			s = "<a href='profile?username=" + username + "'>" + xmlhttp.responseText + "</a>";
			s = s + "<input type='button' class='delete' value='Delete' onclick='deleteAssignee(" + username + "," + taskid + ")'/>";
			newTag.innerHTML = s;
			
			$id('rincian-assignee').childNodes[1].appendChild(newTag);
			
			$id('rincian-assignee-edit-username').value = "";
		}
	}
	
	username = $id('rincian-assignee-edit-username').value;
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=addAssignee&taskID=" + taskid + "&username=" + username,true);
	xmlhttp.send();
}

function sendComment(taskid) {
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
	
	content = $id('rincian-comment-input').value;
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=addComment&taskID=" + taskid + "&content=" + content,true);
	xmlhttp.send();
}