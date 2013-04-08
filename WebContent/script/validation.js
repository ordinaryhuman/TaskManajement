// JavaScript Document

function login(){
	var username = document.getElementById('userlogin').value;
	var password = document.getElementById('passlogin').value;
	if((username=="hanif" && password=="hanif") || (username=="patrick" && password=="patrick") || (username=="novry" && password=="novry"))
   		window.location="src/Dashboard.html";
	else alert("Invalid Username or Password!")
}

function validate_Output(regexp, input, syntaxerror, syntaxvalid, idToWrite) {
	var valid = (regexp.test(input));	
	syntaxvalid = '<span class="valid">' + syntaxvalid + '</span>';
	syntaxerror = '<span class="error">' + syntaxerror + '</span>';
	document.getElementById(idToWrite).innerHTML = valid ? syntaxvalid : syntaxerror;
	return valid;
	if (valid == syntaxvalid) {}
}

function validate_userid() {
		validate_Output(/^.{5,}$/, document.getElementById('userid').value,'INVALID','VALID','name_info');
}

function validate_passid() {
	validate_Output(/^.{8,}$/, document.getElementById('passid').value,'INVALID','VALID','pass_info');
}

function validate_confirmpass() {
	var pw1 = document.getElementById('passid').value;
	var pw2 = document.getElementById('confirmpass').value;
	if(pw2 != pw1)	document.getElementById('confpass_info').innerHTML = '<span class="error">INVALID</span>';
	else			document.getElementById('confpass_info').innerHTML = '<span class="valid">VALID</span>';
}

function validate_fullname() {
	validate_Output(/^[a-zA-Z ]{1,} [a-zA-Z ]{1,}$/,document.getElementById('fullname').value,'INVALID','VALID','fullname_info');
}

function validate_email() {
	validate_Output(/^\w+@(\w+\.)+\w{2,}$/, document.getElementById('email').value,'INVALID','VALID','email_info');
}

function validate_birth() {
	if(!validate_Output(/^\d{4}(-\d{2}){2}$/, document.getElementById('birth').value,'ERROR!','OK','bd_info')) return null;
	date = document.getElementById('birth').value.split('-');
	validDate = new Date(date[0], date[1], date[2]);
	valid = validDate.getFullYear() == date[0] && validDate.getMonth() == date[1] && validDate.getDate() == date[2];
	validate_Output(/^.$/, valid ? 'a' : 'asdf','ERROR!','OK','birth_info');
}

function validate_avatar() {
	validate_Output(/\.(jpg|jpeg)$/, document.getElementById('avatar').value,'INVALID','VALID','avatar_info');
}

function validate_taskname() {
		validate_Output(/^.{1,25}$/, document.getElementById('taskname').value,'INVALID','VALID','taskname_info');
}

function validate_taskfile() {
	validate_Output(/\.(txt|doc|docx|pdf)$/, document.getElementById('taskfile').value,'INVALID','VALID','taskfile_info');
}

function validate_taskpic() {
	validate_Output(/\.(jpg|png|jpeg|bmp)$/, document.getElementById('taskpic').value,'INVALID','VALID','taskpic_info');
}

function validate_taskvideo() {
	validate_Output(/\.(mp4|avi|wmv|mkv)$/, document.getElementById('taskvideo').value,'INVALID','VALID','taskvideo_info');
}