/**
 * @author gmochid
 */
window.onload = function() {
}

function getAvatarPath(username) {
	filename = $id('avatar').value.split('.').pop();
	$id('avatarForm').action = "profile?action=uploadAvatar&username=" + username + "&filename=" + filename;
}

function isChanged() {
	check = Array();
	check[0] = $id("emailDisp").innerHTML == $id("emailEdit").childNodes[0].value;
	check[1] = $id("fullnameDisp").innerHTML == $id("fullnameEdit").childNodes[0].value;
	check[2] = $id("birthdateDisp").innerHTML == $id("birthdateEdit").childNodes[0].value;
	check[3] = "" == $id("oldpass").value;
	for(i = 0; i < check.length; i++) {
		if(!check[i]) {
			return;
		}
	}
	alert('Tidak ada field yang diubah');
}

function toogleEdit() {
	if($id("password").hidden == true) {
		$id("fullnameDisp").hidden = true;
		$id("birthdateDisp").hidden = true;
		$id("emailDisp").hidden = true;
		$id("editInput").hidden = true;
		
		$id("submitInput").hidden = false;
		$id("avatarEdit").hidden = false;
		$id("fullnameEdit").hidden = false;
		$id("birthdateEdit").hidden = false;
		$id("emailEdit").hidden = false;
		$id("password").hidden = false;
	} else {
		$id("fullnameDisp").hidden = false;
		$id("birthdateDisp").hidden = false;
		$id("emailDisp").hidden = false;
		$id("editInput").hidden = false;
		
		$id("submitInput").hidden = true;
		$id("avatarEdit").hidden = true;
		$id("fullnameEdit").hidden = true;
		$id("birthdateEdit").hidden = true;
		$id("emailEdit").hidden = true;
		$id("password").hidden = true;
	}
}
