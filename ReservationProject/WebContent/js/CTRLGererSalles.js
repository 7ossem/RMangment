/**
 * 
 */
$(document).ready(function(){
	$("#searchbuttomsupp").click(function(){
		SearchSalle();
    });
}); 
function ConsulterListSalles() {

	$
			.ajax({
				url : 'gerersalles',
				data : {
					choix : 'ConsulterListSalles',
				},
				type : 'post',
				cache : false,
				success : function(data) {
					alert(data);
					$('#ConsulterListSalles').html(data);
				},

				error : function() {
					$('#ConsulterListSalles')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");

				}
			});
}

// Search Salle

function SearchSalle() {
	
	var word = document.getElementById('searchSS').value;
	alert(word);
	$
			.ajax({
				url : 'gerersalles',
				data : {
					choix : 'SearchSalle',
					word : word
				},
				type : 'post',
				cache : false,
				success : function(data) {
					alert(data);
					$('#resultsearchSalle').html(data);
				},
				error : function() {
					$('#resultsearchSalle').html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");
				}
			});
}
