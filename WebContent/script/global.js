/**
 * @author gmochid
 */

(function($) {
	$.$id = function(id) {
		var ret = document.getElementById(id);
		return ret;
	}
})(window);

function hasClass(x, y) {
	var classes = x.className.split(' ');
	return (classes.indexOf(y) != -1);
}

function addClass(x, y) {
	var classes = x.className.split(' ');
	if(classes.indexOf(y) != -1) {
		return;
	}
	if(x.className === '') {
		x.className += y;
	} else {
		x.className += ' ' + y;
	}
}

function deleteClass(x, y) {
	var classes = x.className.split(' ');
	var index = classes.indexOf(y);
	if(index != -1) {
		classes.splice(index, 1);
		x.className = classes.join(" ");
	}
}

function getElementsByClassName(node,classname) {
  if (node.getElementsByClassName) { // use native implementation if available
    return node.getElementsByClassName(classname);
  } else {
    return (function getElementsByClass(searchClass,node) {
        if ( node == null )
          node = document;
        var classElements = [],
            els = node.getElementsByTagName("*"),
            elsLen = els.length,
            pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)"), i, j;

        for (i = 0, j = 0; i < elsLen; i++) {
          if ( pattern.test(els[i].className) ) {
              classElements[j] = els[i];
              j++;
          }
        }
        return classElements;
    })(classname, node);
  }
}

function deleteAllChildElements(id) {
	var myNode = document.getElementById(id);
	while (myNode.firstChild) {
	    myNode.removeChild(myNode.firstChild);
	}
}

function hideClass(classname) {
	elements = getElementsByClassName(document, classname);
	for(i = 0; i < elements.length; i++) {
		elements[i].style.visibility = 'hidden';
	}
}

function showClass(classname) {
	elements = getElementsByClassName(document, classname);
	for(i = 0; i < elements.length; i++) {
		elements[i].style.visibility = 'visible';
	}
}

function deleteChild(parent, child) {
	parent.removeChild(child);
}
