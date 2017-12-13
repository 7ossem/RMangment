<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="description" content="">
<meta name="author" content="">
<title>Equipment</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap RTL theme -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- font-awesome CSS -->
<!--<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">-->
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/main.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/maintabs.css" rel="stylesheet">
<!--Animate -->
<link href="css/animate.css" rel="stylesheet">
</head>
<body>

	<!-- Nav-bar-->

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <!--<img src="img/logo.png" alt="LOGO"/>-->
			</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<li>
				<!--<a href="#" data-placement="bottom" data-toggle="tooltip" data-original-title="Stats"><i-->
				<!--class="fa fa-bar-chart-o"></i>--> <!--</a>-->
			</li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Admin User <b class="fa fa-angle-down"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Edit
							Profile</a></li>
					<li><a href="#"><i class="fa fa-fw fa-cog"></i> Change
							Password</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-fw fa-power-off"></i>
							Logout</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->

		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a
					href="<%=request.getContextPath()%>/CTRLgererReservation"
					data-target="#submenu-1"> <!--<i class="fa fa-fw fa-search"></i>-->
						Gerer Reservation <i
						class="fa fa-fw fa-chevron-circle-right pull-right"></i></a></li>
				<li><a href="<%=request.getContextPath()%>/gerersalles"
					data-toggle="collapse" data-target="#submenu-2"> Gerer Salles <i
						class="fa fa-fw fa-chevron-circle-right pull-right"></i>
				</a></li>
				<li><a
					href="<%=request.getContextPath()%>/CTRLgererEquipment.jsp?id=gererequipment"
					data-toggle="collapse" data-target="#submenu-3"> Gerer
						Equipment <i class="fa fa-fw fa-chevron-circle-right pull-right"></i>
				</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row" id="main">
					<div class="col-lg-12 col-sm-12">
						<div class="btn-pref btn-group btn-group-justified btn-group-lg"
							role="group" aria-label="...">
							<div class="btn-group" role="group">
								<button type="button" id="stars" class="btn btn-success"
									href="#tab1" data-toggle="tab">
									<span class="fa fa-plus-square fa-5x" aria-hidden="true"></span>
									<div class="hidden-xs">Ajouter</div>
								</button>
							</div>
							<div class="btn-group" role="group">
								<button type="button" id="favorites" class="btn btn-danger"
									href="#tab2" data-toggle="tab">
									<span class="fa fa-trash-o fa-5x" aria-hidden="true"></span>
									<div class="hidden-xs">Supprimer</div>
								</button>
							</div>
							<div class="btn-group" role="group">
								<button type="button" id="following" class="btn btn-warning"
									onclick="GetListModEquipment();" href="#tab3" data-toggle="tab">
									<span class="fa fa-cog fa-5x" aria-hidden="true"></span>
									<div class="hidden-xs">Modifier</div>
								</button>
							</div>
						</div>

						<div class="col-sm-12 well">
							<div class="tab-content">
								<div class="tab-pane fade in active header" id="tab1">
							<h2 class="text-info text-center"> Ajouter Equipment</h2>
									<form action="#">
										<div class="input-group">
											<input class="btn btn-lg" name="eqNom" id="eqNom" type="text"
												placeholder="Equipment ..." required>
											<button class="btn btn-info btn-lg" type="button"
												onclick="AjouterEquipment()">Ajouter</button>
										</div>
									</form>
									<div id="AjouterRes"></div>
								</div>
								<div class="tab-pane fade in header" id="tab2">
									<h2 class="text-center text-info">This is tab 2</h2>
									<div>
										<div class="col-md-12 custyle">
											<div id="supprimerWarning"></div>
											<table class="table table-striped custab">
												<thead>
													<tr>
														<th>ID</th>
														<th>Nom Equipment</th>
														<th class="text-center">Action</th>
													</tr>
												</thead>
												<tbody id="supequipment">

													<tr>
														<td>1</td>
														<td>News</td>
														<td class="text-center"><a href="#"
															class="btn btn-danger btn-xs"> <span
																class="glyphicon glyphicon-remove"></span>Supprimer
														</a></td>
													</tr>

												</tbody>
											</table>
										</div>
									</div>
								</div>
								<div class="tab-pane fade in header" id="tab3">
									<h2 class="text-center text-info">This is tab 3</h2>
									<form action="javascript:void(0);">
										<div class="input-group">
											<input class="btn btn-lg" name="searcheq" id="searcheq"
												type="text" placeholder="Search ..." required>
											<button class="btn btn-success btn-lg" onclick="searchEditEquimnet();" type="submit">
												<i class="glyphicon glyphicon-search" ></i>&nbsp;&nbsp;&nbsp;Search
											</button>
										</div>
									</form>
									<div>
										<div class="col-md-12 custyle">
											<div id="modequipment"></div>
											<table class="table table-striped custab">
												<thead>
													<tr>
														<th>ID</th>
														<th>Nom Equipment</th>
														<th class="text-center">Action</th>
													</tr>
												</thead>
												<tbody id="listmodequipment">
													<tr>
														<td>1</td>
														<td>News</td>
														<td class="text-center"><a href="#"
															class="btn btn-warning btn-xs"> <span
																class="glyphicon glyphicon-edit"></span>Edit
														</a></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- Modal -->
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true" id="mi-modal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Confirmar</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="modal-btn-si">Si</button>
					<button type="button" class="btn btn-primary" id="modal-btn-no">No</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/sweetalert.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/CTRLGererEquipment.js"></script>
	<script type="text/javascript">
	<!--
		
	//-->
	</script>
</body>
</html>

