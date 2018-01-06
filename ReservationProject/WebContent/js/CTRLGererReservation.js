$(document).ready(function() {
	GetFormSearch();
	GetFormSearchAnnuler();
	// $("#search-adv").click(function(){
	// SearchSalleAdvanced();
	// });
})
function LogOut(){
	window.location.href = 'http://localhost:8085/ReservationProject/'
		+ 'Deconnexion';
}

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
					if (data === 'sessionfalse'){
						window.location.href = 'http://localhost:8085/ReservationProject/'
							+ 'Deconnexion';
					}
					
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

/* Append Form Search annuler */

function GetFormSearchAnnuler() {
	$
			.ajax({
				url : 'gererReservation',
				data : {
					choix : 'FormSearcha'
				},
				type : 'post',
				cache : false,
				success : function(data) {
					if (data === 'sessionfalse'){
						window.location.href = 'http://localhost:8085/ReservationProject/'
							+ 'Deconnexion';
					}
					$('#formsearcha').html(data);
				
				},
				error : function() {
					$('#formsearcha')
							.html(
									"<div class='alert alert-danger alert-dismissable fade in' >"
											+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
											+ "<strong>Error : </strong> Server Not Responed !</div>");
				}
			});
}

function SearchSalleAdvanced() {
	var meunselectionjour = document.getElementById("Jour-reservation");
	var jour_id = meunselectionjour.options[meunselectionjour.selectedIndex].value;
	// alert(jour_id);
	var meunselectiontime = document.getElementById("time-reservation");
	var time_id = meunselectiontime.options[meunselectiontime.selectedIndex].value;
	// alert(time_id);
	var capacite = document.getElementById("capacites").value;
	// alert(capacite);
	$.ajax({
		url : 'gererReservation',
		data : {
			choix : 'SearchSalleAdvanced',
			jour_id : jour_id,
			time_id : time_id,
			capacite : capacite
		},
		type : 'post',
		cache : false,
		success : function(data) {
			$('#resSearchSalle').html(data);
			// alert(data);
		},
		error : function() {
			swal("danger", "Exception Internal Error!", "error");
		}
	});
}
// reserver salle
function ReserverSalles(idsalle, idperiode, idjour, iddiv) {

	$.ajax({
		url : 'gererReservation',
		data : {
			choix : 'reserverSalle',
			idsalle : idsalle,
			idperiode : idperiode,
			idjour : idjour
		},
		type : 'post',
		cache : false,
		success : function(data) {
			if (data === 'sessionfalse'){
				window.location.href = 'http://localhost:8085/ReservationProject/'
					+ 'Deconnexion';
			}
			if (data == 1) {
				swal("La salle est resrver", {
					icon : "success",
				});

				$('#c' + idsalle + '').animate({
					buttom : '100px'
				}).fadeOut(2000);

			} else {
				swal({
					text : "Remplir Correctement les champ!",
					icon : "warning",
					button : true,
					dangerMode : true,
				});
			}

		},
		error : function() {
			swal("danger", "Exception Internal Error!", "error");

		}
	});
}

//
// function SearchSalles() {
// var meunselectionjour = document.getElementById("Jour-reservation");
// var jour_id =
// meunselectionjour.options[meunselectionjour.selectedIndex].value;
// // alert(jour_id);
// var meunselectiontime = document.getElementById("time-reservation");
// var time_id =
// meunselectiontime.options[meunselectiontime.selectedIndex].value;
// // alert(time_id);
// var capacite = document.getElementById("capacites").value;
// $
// .ajax({
// url : 'gererReservation',
// data : {
// choix : 'SearchSalleAdvanced',
// jour_id : jour_id,
// time_id : time_id,
// capacite : capacite
// },
// type : 'post',
// cache : false,
// success : function(data) {
// $('#resSearchSalle').html(data);
// },
// error : function() {
// swal("danger!", "Exception Internal Error!", "danger");
//
// }
// });
// }
function SearchSalles() {
	var meunselectionjour = document.getElementById("Jour-reservation");
	var jour_id = meunselectionjour.options[meunselectionjour.selectedIndex].value;
	// alert(jour_id);
	var meunselectiontime = document.getElementById("time-reservation");
	var time_id = meunselectiontime.options[meunselectiontime.selectedIndex].value;
	// alert(time_id);
	var capacite = document.getElementById("capacites").value;

	$.ajax({
		url : 'gererReservation',
		data : {
			choix : 'SearchSalleAdvanced',
			jour_id : jour_id,
			time_id : time_id,
			capacite : capacite
		},
		type : 'post',
		cache : false,
		success : function(data) {
			if (data === 'sessionfalse'){
				window.location.href = 'http://localhost:8085/ReservationProject/'
					+ 'Deconnexion';
			}
			$('#resSearchSalle').html(data);
		},
		error : function() {
			swal("danger", "Exception Internal Error!", "error");
		}
	});

}

// search salle a annuler

//
// function SearchSalles() {
// var meunselectionjour = document.getElementById("Jour-reservation-a");
// var jour_id =
// meunselectionjour.options[meunselectionjour.selectedIndex].value;
// // alert(jour_id);
// var meunselectiontime = document.getElementById("time-reservation-a");
// var time_id =
// meunselectiontime.options[meunselectiontime.selectedIndex].value;
// // alert(time_id);
// var capacite = document.getElementById("capacites-a").value;
// $
// .ajax({
// url : 'gererReservation',
// data : {
// choix : '',
// jour_id : jour_id,
// time_id : time_id,
// capacite : capacite
// },
// type : 'post',
// cache : false,
// success : function(data) {
// $('#resSearchSalle').html(data);
// },
// error : function() {
// swal("danger!", "Exception Internal Error!", "danger");
//
// }
// });
// }
//
//

function AnnulerReserverSalleddddd(idsalle, idperiode, idjour, iddiv) {

	$.ajax({
		url : 'gererReservation',
		data : {
			choix : 'AnnulerreserverSalle',
			idsalle : idsalle,
			idperiode : idperiode,
			idjour : idjour
		},
		type : 'post',
		cache : false,
		success : function(data) {
			if (data === 'sessionfalse'){
				window.location.href = 'http://localhost:8085/ReservationProject/'
					+ 'Deconnexion';
			}
			if (data == 1) {
				swal("La salle est Liberer ", {
					icon : "success",
				});

				$('#ca' + idsalle + '').animate({
					buttom : '100px'
				}).fadeOut(2000);

			} else {
				swal({
					text : "Operation non Complete Correctement les champ!",
					icon : "warning",
					button : true,
					dangerMode : true,
				});
			}

		},
		error : function() {
			swal("danger", "Exception Internal Error!", "error");

		}
	});
}

// search to annuler
function SearchSallesa() {
	var meunselectionjour = document.getElementById("Jour-reservation-a");
	var jour_id = meunselectionjour.options[meunselectionjour.selectedIndex].value;
	// alert(jour_id);
	var meunselectiontime = document.getElementById("time-reservation-a");
	var time_id = meunselectiontime.options[meunselectiontime.selectedIndex].value;
	// alert(time_id);
	var capacite = document.getElementById("capacites-a").value;
	var numsalle = document.getElementById("num-salle-a").value;
	if (numsalle > 0) {

		$.ajax({
			url : 'gererReservation',
			data : {
				choix : 'SearchSalleAdvancedAnnuler',
				jour_id : jour_id,
				time_id : time_id,
				capacite : capacite,
				num_salle :numsalle
			},
			type : 'post',
			cache : false,
			success : function(data) {
				if (data === 'sessionfalse'){
					window.location.href = 'http://localhost:8085/ReservationProject/'
						+ 'Deconnexion';
				}
		
				$('#resSearchSallea').html(data);
			},
			error : function() {
				swal("danger", "Exception Internal Error!", "error");

			}
		});
	}
	else {
		swal("error", "Remplir Correct NumrSalle", "error");
	
	}
	}

	function AnnulerReserverSalle(idsalle, idperiode, idjour, iddiv) {

		$.ajax({
					url : 'gererReservation',
					data : {
						choix : 'AnnulerreservationSalle',
						idsalle : idsalle,
						idperiode : idperiode,
						idjour : idjour
					},
					type : 'post',
					cache : false,
					success : function(data) {
						if (data == 1) {
							if (data === 'sessionfalse'){
								window.location.href = 'http://localhost:8085/ReservationProject/'
									+ 'Deconnexion';
							}
							swal("La salle est Liberer ", {
								icon : "success",
							});
							$('#ca' + idsalle + '').animate({
								buttom : '100px'
							}).fadeOut(2000);
							
						} else {
							swal({
								text : "Operation non Complete Correctement les champs!",
								icon : "warning",
								button : true,
								dangerMode : true,
							});
						}
					},
					error : function() {
						swal("danger", "Exception Internal Error!", "error");
					}
				});
	}
