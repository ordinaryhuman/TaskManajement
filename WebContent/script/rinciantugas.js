/**
 * @author gmochid
 */
window.onload = function() {
	$id("rinciantugas-taskid").style.visibility = 'hidden';
	$id("rincianinput-form-tag").style.visibility = 'hidden';
	$id("rincianinput-form-deadline").style.visibility = 'hidden';
	$id("rincianinput-form-assignee").style.visibility = 'hidden';
	$id("rincianinput-form-attachment").style.visibility = 'hidden';
	$id("rincian-deadline").innerHTML = $id("rincianinput-deadline").value;
	$id("rincianbutton-save").style.visibility = 'hidden';
	
	hideClass('delete');
	tagHints();
	assigneeHints();
}

function edittask() {
	$id("rincianinput-form-tag").style.visibility = 'visible';
	$id("rincianinput-form-deadline").style.visibility = 'visible';
	$id("rincianinput-form-assignee").style.visibility = 'visible';
	$id("rincianinput-form-attachment").style.visibility = 'visible';
	$id("rincian-deadline").style.visibility = 'hidden';
	
	$id("rincianbutton-save").style.visibility = 'visible';
	$id("rincianbutton-edit").style.visibility = 'hidden';	
	
	showClass('delete');
}

function savetask() {
	$id("rincianinput-form-tag").style.visibility = 'hidden';
	$id("rincianinput-form-deadline").style.visibility = 'hidden';
	$id("rincianinput-form-assignee").style.visibility = 'hidden';
	$id("rincianinput-form-attachment").style.visibility = 'hidden';
	$id("rincian-deadline").style.visibility = 'visible';
	
	$id("rincianbutton-save").style.visibility = 'hidden';
	$id("rincianbutton-edit").style.visibility = 'visible';	
	
	hideClass('delete');
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
	xmlhttp.open("GET","rinciantugasAJAX.php?action=tagHint&taskid=5",true);
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
