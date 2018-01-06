$(function() {
	GetListSuppEquipment();
});

function LogOut(){

	window.location.href = 'http://localhost:8085/ReservationProject/'
		+ 'Deconnexion';
}
/**
 * Add Equipment
 */
function AjouterEquipment() {
	var equip = document.getElementById("eqNom");
	// var TypeDoc = document.getElementById("typeDoc");
	// var TypeDocValue = TypeDoc.options[TypeDoc.selectedIndex].value;
	$
			.ajax({
				url : 'gererEquipment',
				data : {
					choix : 'Ajouterequipment',
					equipment : equip.value
				},
				type : 'post',
			
				cache : false,
				success : function(data) {
					if (data === 'sessionfalse'){
						window.location.href = 'http://localhost:8085/ReservationProject/'
							+ 'Deconnexion';
					}
					$('#AjouterRes').html(data);
					GetListSuppEquipment();
				},

				error : function() {
					// $('#AjouterRes').html("<div class='alert
					// alert-danger'><strong> Error :</strong>Not Respnses
					// internalt Error</div> ");

					$('#AjouterRes')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");

				}
			});
}

/**
 * Confirm Supprimer
 * 
 * @param id
 * 
 */
function ConfirmSupprimer(id){
	swal({
		  title: "Are you sure?",
		  text: "Once deleted, you will not be able to recover this imaginary file!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			  SupprimerEquipment(id);
			  
		    swal("Poof! Your imaginary file has been deleted!", {
		      icon: "success",
		    });
		  } else {
		    swal("Your imaginary file is safe!");
		  }
		});
}
/**
 * Supprimer Equipmenet
 */
function SupprimerEquipment(id) {
	var equip = id;
	// var r = confirm("Vous Voulez Supprimer L'\equipment ?!");
		$.ajax({
					url : 'gererEquipment',
					data : {
						choix : 'SupprimerEquipment',
						equipment : id
					},
					type : 'post',
					cache : false,
					success : function(data) {
						if (data === 'sessionfalse'){
							window.location.href = 'http://localhost:8085/ReservationProject/'
								+ 'Deconnexion';
						}
						$('#supprimerWarning').html(data);
						GetListSuppEquipment();
					},
					error : function() {
						$('#supprimerWarning')
								.html("<div class='alert alert-danger alert-dismissable fade in' >"
												+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
												+ "<strong>Error : </strong> Server Not Responed !</div>");

					}
				});
}

/**
 * Get List Of Componenet to Remove
 * 
 */

function GetListSuppEquipment() {

	$
			.ajax({
				url : 'gererEquipment',
				data : {
					choix : 'GetListSuppEquipmnet',
				},
				type : 'post',
				cache : false,
				success : function(data) {
					if (data === 'sessionfalse'){
						window.location.href = 'http://localhost:8085/ReservationProject/'
							+ 'Deconnexion';
					}
					$('#supequipment').html(data);
				},

				error : function() {
					// $('#AjouterRes').html("<div class='alert
					// alert-danger'><strong> Error :</strong>Not Respnses
					// internalt Error</div> ");
					$('#supequipment')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");

				}
			});
}


/**
 * Get List Of Componenet to Remove
 * 
 */
function GetListModEquipment() {

	$
			.ajax({
				url : 'gererEquipment',
				data : {
					choix : 'GetListModEquipment',
				},
				type : 'post',
				cache : false,
				success : function(data) {
					if (data === 'sessionfalse'){
						window.location.href = 'http://localhost:8085/ReservationProject/'
							+ 'Deconnexion';
					}
					$('#listmodequipment').html(data);
				},

				error : function() {
					
					$('#listmodequipment')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");

				}
			});
}

/**
 * Edit Component
 */
function EditEquipment(id,nom) {
	 $.ajax({
  url : 'gererEquipment',
 data : {
 choix : 'EditEquipment',
 id : id,
 nom: nom
 },
 type : 'post',
 cache : false,
 success : function(data) {
	 if (data === 'sessionfalse'){
			window.location.href = 'http://localhost:8085/ReservationProject/'
				+ 'Deconnexion';
		}
	 if (data == 1){
		 swal("Equipment has been Edited!", {
		      icon: "success",
		    }); 
		 GetListModEquipment();
	 }else {
		 swal("Wrong Not Edited Internal Erro", {
		      icon: "danger",
		    });  
	 }
 },

 error : function() {
	 swal("Wrong Not Edited Internal Erro Server Not Respond", {
	      icon: "danger",
	    }); 
 }
 });
}
/**
 * Modal Editing
 * @param id
 * @returns
 */
function ModifierEquipmnet(id){
	var nom;
	swal({
	 
		title : "Edit Component",
		text :  "Edit equipment ("+id+") : " ,
		  content: {
		    element: "input",
		    attributes: {
		    	id: "NouvNom",
		    	placeholder: " nouveaux nom",
		    },
		  },
		})
		.then(name => {
	 		if (!name)	
				 return swal({
					  text: "Vide !",
					  icon: "warning",
					  button: true,
					  dangerMode: true,
					});
			 nom = document.getElementById("NouvNom").value;
			 EditEquipment(id,nom);
		})
}

function searchEditEquimnet(){
	var word = document.getElementById("searcheq").value;
	if(word.length >= 0 ){
	$.ajax({
		  url : 'gererEquipment',
		 data : {
		 choix : 'searchEditEquipment',
		 WordSearch: word
		 },
		 type : 'post',
		 cache : false,
		 success : function(data) {
			 if (data === 'sessionfalse'){
					window.location.href = 'http://localhost:8085/ReservationProject/'
						+ 'Deconnexion';
				}
				$('#listmodequipment').html(data);
			},

			error : function() {
				
				$('#listmodequipment')
						.html(
								"<div class='alert alert-danger alert-dismissable fade in' >"
										+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
										+ "<strong>Error : </strong> Server Not Responed !</div>");

			}
		});
}else {
	swal({
		  text: "Entrer Mote a chercher!",
		  icon: "warning",
		  button: true,
		  dangerMode: true,
		});
}
	}