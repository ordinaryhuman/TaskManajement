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
	  
	  $id('rincian-attachment-form').action = "task?action=addAttachmentFromAddTask&taskID=" + taskid + 
	    "&file-name=" + sourceFilename + "&type=" + type;
	}
