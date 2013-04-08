/**
 * @author gmochid
 */
window.onload = function() {
	window.selectedCategory = $id('category-all');
	$id("addtask_button").style.visibility = 'hidden';
}

function selectCategory(selected) {
	if(selected == null) {
		window.selectedCategory = $id('category-all');
		$id("addtask_button").style.visibility = 'hidden';
	} else {
		window.selectedCategory = $id(selected);
		$id("addtask_button").style.visibility = 'visible';
		link = $id("addtask_button").href.split("?");
		$id("addtask_button").href = link[0] + "?categoryID=" + selected;
	}
}
