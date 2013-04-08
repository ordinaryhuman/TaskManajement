/**
 * @author gmochid
 */

window.onload = function() {
	$id('addtugas-categoryid').style.visibility="hidden";
	getAssigneeHint();
}

function isValid() {
	var requirement = Array();
	
	requirement[0] = ($id('taskname').value.length != 0);
	requirement[1] = ($id('bir').value.length != 0);
	
	for(i = 0; i < requirement.length; i++) {
		if(!requirement[i])
			return false;
	}
	return true;
}

function checkInput() {
	if(isValid()) {
		$id('addtugas-submit').disabled = false;
	} else {
		$id('addtugas-submit').disabled = true;
	}
}

function getAssigneeHint() {
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
			
			arr = $id('addtugas-assignee').innerHTML.split(','); 
			
			for(i = 0; i < response.length; i++) {
				if(arr.indexOf(response[i]) == -1) {
					x = document.createElement('option');
					x.value = response[i];
					$id('hintlist-assignee').appendChild(x);
				}
			}
		}
	}
	
	categoryid = $id('addtugas-categoryid').innerHTML;
	xmlhttp.open("GET","addtugasAJAX.php?action=assigneeHint&categoryid=" + categoryid, true);
	xmlhttp.send();
}

function addAssignee() {
	if($id('addtugas-assignee').innerHTML == "") {
		$id('addtugas-assignee').innerHTML += $id('addtugas-assigneeinput').value;
	} else {
		$id('addtugas-assignee').innerHTML += "," + $id('addtugas-assigneeinput').value;
	}
	getAssigneeHint();
}
