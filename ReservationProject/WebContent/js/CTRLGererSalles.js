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
				// alert(data);
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
				// alert(data);
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

/* Supprimer Salle */

function ConfirmSalle(id){
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this imaginary file!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  
			  SupprimerSalle(id);
			  
		    swal("Poof! Your  Room has been deleted!", {
		      icon: "success",
		    });
		  } else {
		    swal("Your imaginary file is safe!");
		  }
		});
}
/**
 * Supprimer Salle
 */

function SupprimerSalle(id) {
	var idsalle = id;
		$.ajax({
					url  : 'gerersalles',
					data : {
						choix : 'supprimersalle',
						idsalle : idsalle
					},
					type : 'post',
					cache : false,
					success : function(data) {
						if(data == "success")
					    document.getElementById('supptr'+id).style.display = 'none';
					},
					error : function() {
						swal({
							  text: "internal Error !",
							  icon: "warning",
							  button: true,
							  dangerMode: true,
							});
					}
				});
}

/* Ajouter salle */

function AjouterSalle() {
	var num = document.getElementById('nums');
	var capacite = document.getElementById('capsts');
		$.ajax({
					url  : 'gerersalles',
					data : {
						choix : 'ajoutersalle',
						num : num.value,
						capacite: capacite.value
					},
					type : 'post',
					cache : false,
					success : function(data) {
						switch(data){
						case '1' :
						    document.getElementById('nums').value='';
							document.getElementById('capsts').value='';
							swal("Success!", "Success Ajoute!", "success");

							break;
						case '-1' : 
							swal("warning!",
									"Salle Deja Exist!!",
									"warning");	
							break;
							
						case '0' : 
							swal("danger!",
									"Exception Internal Error!",
									"danger");	
							break;
							
						}
						
					},
					error : function() {
						swal({
							  text: "Internal Error !",
							  icon: "warning",
							  button: true,
							  dangerMode: true,
							});
					}
				});
}
