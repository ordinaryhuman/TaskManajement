/**
 * @author gmochid
 */
window.onload = function() {
	//tagHints();
	//assigneeHints();
	
	len = $id('comment-length').value;
	
	$id('button_previous').style.visibility = "hidden";
	if(len <= 10) {
		$id('button_next').style.visibility = "hidden";
	}
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
	
	$id('rincianbutton-delete').hidden = !$id('rincianbutton-delete').hidden; 
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
	
	content = $id('rincian-comment-input').value;
	
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
			comment_pages(taskid, 0);
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=addComment&taskID=" + taskid + "&content=" + content,true);
	xmlhttp.send();
}

function comment_pages(taskid, page) {
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
			
			console.log(xmlhttp.responseText);
			
			$id('rincian-comment-list').innerHTML = "";
			for(i = 0; i < response.length; i++) {
				newCom = document.createElement('div');
				newCom.id = "rincian-comment-list-" + response[i].id;
				newCom.className = "commentbox";
				
				s = '<img src="upload/avatars/' + response[i].avatarPath + '" class="commentuser">';
				s = s + '<div class="nameuser">' + response[i].username + ' (' + response[i].timestamp + ')</div>';
				s = s + '<div class="comment">' + response[i].content + '</div>';
				
				newCom.innerHTML = s;
				
				$id('rincian-comment-list').appendChild(newCom);
			}
			
			len = $id('comment-length').value;
			len = len - len %10;
			
			if(page == 0) {
				$id('button_previous').style.visibility = "hidden";
			} else {
				$id('button_previous').style.visibility = "visible";
				$id('button_previous').onclick = function() { comment_pages(taskid, page - 1);};
			}
			
			if((len / 10) == page) {
				$id('button_next').style.visibility = "hidden";
			} else {
				$id('button_next').style.visibility = "visible";
				$id('button_next').onclick = function() { comment_pages(taskid, page + 1);};
			}
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=getCommentPages&taskID=" + taskid + "&pages=" + page,true);
	xmlhttp.send();
}

function setType(taskid) {
	var type;
	if($id('rincian-attachment-type-file').checked) {
		type = "file";
	} else if($id('rincian-attachment-type-video').checked) {
		type = "video";
	} else {
		type = "image";
	}
	
	sourcePath = $id('rincian-attachment-path').value;
	sourceFilename = (sourcePath == null) ? "" : sourcePath.split('\\').pop();
	
	$id('rincian-attachment-form').action = "task?action=addAttachment&taskID=" + taskid + 
		"&file-name=" + sourceFilename + "&type=" + type;
}
/*
function addAttachment(taskid) {
	var xmlhttp;
	
	var type;
	if($id('rincian-attachment-type-file').checked) {
		type = "file";
	} else if($id('rincian-attachment-type-video').checked) {
		type = "video";
	} else {
		type = "image";
	}
	
	sourcePath = $id('rincian-attachment-path').value;
	sourceFilename = sourcePath.split('\\').pop();
	console.log(sourceFilename);
	
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
			response = JSON.parse(xmlhttp.responseText)
			
			newElm = document.createElement("div");
			newElm.id = 'rincian-attachment-' + response.id;
			
			s = "";
			
    		if(type == "file") {
    			s = s + "<a href='" + response.destPath + "'> " + response.destPath.split('/').pop() + " </a>";
    			s = s + "<br>";
    		} else if(type == "image") {
    			s = s + response.destPath.split('/').pop() + "<br>";
    			s = s + "<img src='" + response.destPath + "'></img>";
    			s = s + "<br>";
    		} else if(type == "video") {
    			s = s + response.destPath.split('/').pop() + "<br>";
    			s = s + "<video width='320' height='240' controls>";
    			s = s + "<source src='" + response.destPath + "'>";
    			s = s + "</video>";
    			s = s + "<br>";
    		}
    		
    		s = s + "<input type='button' class='delete' value='Delete' onclick='deleteAttachment(" + response.id + ")'/>";
    		s = s + "<br><br>";
    		
    		newElm.innerHTML = s;
    		
    		$id('rincian-attachment').appendChild = newElm;
		}
	}
	
	xmlhttp.open("GET","ajaxHandler/detailTask.jsp?action=addAttachment&taskID=" + taskid + "&type=" + type +
			"&sourcePath=" + sourcePath + "&sourceFilename=" + sourceFilename, true);
	xmlhttp.send();	
}
*/