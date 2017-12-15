/**
 * 
 */
$(document).ready(function(){
	GetListEquipments();
	$("#searchbuttomsupp").click(function(){
		 $("#resultsearchSalle").hide();
	     SearchSalle();
	     $("#resultsearchSalle").fadeIn(2000);
    });
//	$("#Numse").click(function(){
//	
//    });
	$(document).on('click','#Numse',function(e){
		   e.preventDefault();
		   console.log('1');
		      //Code 
			AjouterEquipmnetSalle(); console.log('3');
		   });
	//Ajouterequip
	$("#searchajeq").click(function(){
		SearchSalleAjEq();
    });
	$("#AjuoterSalles").click(function(){
		AjouterSalle();
    });
}); 
function ConsulterListSalles() {

	$.ajax({
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

// Search Salle a supprimer
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
/* Seach salle to add component */

// Search Salle
function SearchSalleAjEq() {
	
	var word = document.getElementById('searchsajeq').value;

	$.ajax({
				url : 'gerersalles',
				data : {
					choix : 'SearchSalleAjEq',
					word : word
				},
				type : 'post',
				cache : false,
				success : function(data) {
				// alert(data);
					$('#resultsearchsajeq').html(data);
				},
				error : function() {
					$('#resultsearchsajeq').html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");
				}
			});
}

/* Ajouter Equipment a La salle */

function AjouterEquipmnetSalle(){
	  console.log('excutefunction');
	  
	var id_salle = document.getElementById('id_salle').value;
	var numbereq = document.getElementById('numbereq').value;
	var meunselection = document.getElementById("idequipment");
	var typeequipment = meunselection.options[meunselection.selectedIndex].value;
    console.log('AjouterEquipmnetSalle type idequip=> '+typeequipment);
    console.log('AjouterEquipmnetSalle numeroequi=>  ' +numbereq);
    console.log('AjouterEquipmnetSall id_sallee=>  '+id_salle);
	
    $.ajax({
		url : 'gerersalles',
		data : {
			choix : 'AjouterEquipmnetSalle',
			numbereq: numbereq,
			typeequipment: typeequipment,
			id_salle:id_salle
		},
		type : 'post',
		cache : false,
		success : function(data) {
			$('#myModalAddEq').modal('hide'); 
		//	document.getElementById('myModalAddEq').style.display='none';
			swal("Success!", "Success Ajoute Equipment!"+data, "success");
			GetSalle(id_salle);
		},
		error : function() {
			swal("danger!",
				"Exception Internal Error!",
					"danger");
		}
	});
//	$.ajax({
//					url  : 'gerersalles',
//					data : {
//						choix : 'AjouterEquipmnetSalle',
//						numbereq : numbereq,
//						typeequipment: typeequipment,
//						id: id
//					},
//					type : 'post',
//					cache : false,
//					success : function(data) {
//						switch(data){
//						case '1' :
//						   
//							swal("Success!", "Success Ajoute Equipment!", "success");
//							GetSalle(id);
//							break;
//						case '-1' : 
//							swal("warning!",
//									"Error Deja Exist!!",
//									"warning");	
//							break;
//							
//						case '0' : 
//							swal("danger!",
//									"Exception Internal Error!",
//									"danger");	
//							break;
//							
//						}
//						
//					},
//					error : function() {
//						swal({
//							  text: "Internal Error !",
//							  icon: "warning",
//							  button: true,
//							  dangerMode: true,
//							});
//					}
//				});
	
}
function setSalleID(id)
{
	console.log(id);
document.getElementById("id_salle").value= id;
}
/* Get Lsit of Component */

function GetListEquipments() {

	$.ajax({
				url : 'gerersalles',
				data : {
					choix : 'GetListequipment',
				},
				type : 'post',
				cache : false,
				success : function(data) {
					$('#idequipment').html(data);
				},
	error : function() {
					$('#idequipment')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");

				}
			});
}

/* Get Salle apres ajoute Equipment */

function GetSalle(id) {
	
	var word = id;

	$.ajax({
				url : 'gerersalles',
				data : {
					choix : 'getSalle',
					word : word
				},
				type : 'post',
				cache : false,
				success : function(data) {
				// alert(data);
					$('#resultsearchsajeq').html(data);
				},
				error : function() {
					$('#resultsearchsajeq').html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");
				}
			});
}
