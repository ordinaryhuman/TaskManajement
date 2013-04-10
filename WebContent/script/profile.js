/**
 * @author gmochid
 */
window.onload = function() {
	
}

function toogleEdit() {
	if($id("password").hidden == true) {
		$id("fullnameDisp").hidden = true;
		$id("birthdateDisp").hidden = true;
		$id("emailDisp").hidden = true;
		
		$id("avatarEdit").hidden = false;
		$id("fullnameEdit").hidden = false;
		$id("birthdateEdit").hidden = false;
		$id("emailEdit").hidden = false;
		$id("password").hidden = false;
	} else {
		$id("fullnameDisp").hidden = false;
		$id("birthdateDisp").hidden = false;
		$id("emailDisp").hidden = false;
		
		$id("avatarEdit").hidden = true;
		$id("fullnameEdit").hidden = true;
		$id("birthdateEdit").hidden = true;
		$id("emailEdit").hidden = true;
		$id("password").hidden = true;
	}
}
