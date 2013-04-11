<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Make a Task</title>
    <link rel='stylesheet' type="text/css" href="style/Design.css"/>
    <script type="text/javascript" src="script/global.js"> </script>
    <script type="text/javascript" src="script/validation.js"> </script>
    <script type="text/javascript" src="script/calendar.js"></script>
    <script type="text/javascript" src="script/addtugas.js"> </script>
</head>

<body class="main">

<!-- HEADER -->
<jsp:include page="../pages/header.jsp"></jsp:include>

<div class="task1">	
	<div class="task2">
		<form action="addtugas2.php" method="post" enctype="multipart/form-data">
		<div class="maketask">
			<div class="fieldhead">
				<h2>Make a Task</h2>
				<h4><?php echo $category->name ?></h4>
			</div>
			<hr>
            <div class="field1">
            	Task Name
            </div>
            <div class="iinfo" id="taskname_info"></div>
            <div class="field">
				<input type="text" id="taskname" onkeyup="checkInput()" onchange="validate_taskname()" name="name"/>
			</div>
			<div class="fieldhelp">
				Maksimal 25 karakter. Tidak boleh menggunakan karakter khusus.
			</div>
            
			<div class="field1">
					File
			</div>
            <div class="iinfo" id="taskfile_info"></div>
			<div class="fieldfile">
				<input type="file" id="taskfile" onchange="validate_taskfile()" name="attachment"/>
			</div>
			<div class="fieldhelp">
				*.txt;*.doc;*.docx;*.pdf
			</div>
			<div class="field1">
				Pic
			</div>
            <div class="iinfo" id="taskpic_info"></div>
			<div class="fieldfile">
				<input type="file" id="taskpic" onchange="validate_taskpic()" name="attachment2"/>
			</div>
			<div class="fieldhelp">
				*.png;*.bmp;*.jpg;*.jpeg
			</div>
			<div class="field1">
				Video
			</div>
            <div class="iinfo" id="taskvideo_info"></div>
			<div class="fieldfile">
				<input type="file" id="taskvideo" onchange="validate_taskvideo()" name="attachment3"/>
			</div>
			<div class="fieldhelp">
				*.mp4;*.avi;*.wmv;*.mkv
			</div>
			<div class="field1">
				Deadline
            </div>
            <div class="iinfo" id="birth"></div>
            <div class="field">
				<input type="text" id="bir" class="birthcal" onchange="checkInput()" name="deadline">
                <input type="button" class="cal" onclick="openCal()" value="calendar"/>
                
                <!-- CALENDAR -->
						<div id="floatcal">
							<script type="text/javascript">
							//<![CDATA[
								var cal = new Calendar(
									new Date(), 
									document.getElementById("floatcal"),
									function(date){
										var yyyy = "" + date.getFullYear();
										while(yyyy.length < 4) yyyy = "0" + yyyy;
										var mm = "" + (date.getMonth() + 1); 
										while(mm.length < 2) mm = "0" + mm;
										var dd = "" + date.getDate();
										while(dd.length < 2) dd = "0" + dd;
										document.getElementById('bir').value = yyyy + "-" + mm + "-" + dd;
										openCal();
										//validateBirthdate();
									}
								);
								
								function openCal() {
									if(cal.isVisible) cal.hideCalendar();
									else cal.drawCalendar();
								}
							//]]>
							</script>
						</div>
                
                
			</div>
            <div class="fieldhelp">
				YYYY-MM-DD
			</div>
			<div class="field1">
				Assignee
            </div>
            <div class="field">
            	<a id="addtugas-assignee"></a>
            	<br>
				<input id="addtugas-assigneeinput" type="textarea" name="assignee" list="hintlist-assignee">
   	    		<datalist id="hintlist-assignee"></datalist>
				<input type="button" onclick="addAssignee()" value="Add"/>
			</div>
            <div class="fieldhelp">
				Peserta Tugas
			</div>
			<div class="field1">
				Tag
            </div>
            <div class="field">
				<input type="textarea" name="tag">
			</div>
			<div class="fieldhelp">
				Lebih dari satu dipisahkan dengan koma ','
			</div>
            
			<div class="field1">
				<input type="text" name="categoryID" value="<?php echo $category->id; ?>" style="visibility: hidden"/><br>
				<input id="addtugas-submit" type="submit" value="Save" disabled/>
			</div>
            
		</div>
		</form>
	</div>
</div>

<div class="footer">This Website is created for Internet Programming Assignment<br />
    By Abdurrosyid Broto Handoyo, Rubiano Adityas, Novriady Saputra<br />
    Maret 2013
</div>
<a id='addtugas-categoryid'><?php echo $category->id; ?></a>
</body>
</html>