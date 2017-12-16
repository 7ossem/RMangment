$(document).ready(function() {
	GetFormSearch();
//	$("#search-adv").click(function(){
//		SearchSalleAdvanced();
//   });
})

/* Append Form Search */

function GetFormSearch() {
	$
			.ajax({
				url : 'gererReservation',
				data : {
					choix : 'FormSearch'
				},
				type : 'post',
				cache : false,
				success : function(data) {
					$('#formsearch').html(data);
				},
				error : function() {
					$('#formsearch')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");
				}
			});
}


function SearchSalleAdvanced(){
	  
//	var id_salle = document.getElementById('id_salle').value;
//var numbereq = document.getElementById('numbereq').value;
	
	  var meunselectionjour = document.getElementById("Jour-reservation");
	  var jour_id = meunselectionjour.options[meunselectionjour.selectedIndex].value;
	 // alert(jour_id);	  
	  var meunselectiontime = document.getElementById("time-reservation");
	  var time_id = meunselectiontime.options[meunselectiontime.selectedIndex].value;
	  //alert(time_id);
	  var capacite = document.getElementById("capacites").value;
	  //alert(capacite);
    $.ajax({
		url : 'gererReservation',
		data : {
			choix : 'SearchSalleAdvanced',
			jour_id: jour_id,
			time_id: time_id,
			capacite:capacite
		},
		type : 'post',
		cache : false,
		success : function(data) {

			swal("Success!", "Success  !"+data, "success");

		},
		error : function() {
			swal("danger!",
				"Exception Internal Error!",
					"danger");
		}
	});
}