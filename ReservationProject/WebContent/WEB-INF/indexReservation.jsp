<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="author" content="">
<title>Managment</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap RTL theme -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- font-awesome CSS -->
<!--<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">-->
<link href="css/font-awesome.min.css" rel="stylesheet">
<!-- for search form -->
<link href="css/forsearch.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/main.css" rel="stylesheet">
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
			<a class="navbar-brand" href="#"> <img src="img/logo.png"
				alt="LOGO" />
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
					<div class="col-sm-12 col-md-12 well" id="content">

						<div class="btn-pref btn-group btn-group-justified btn-group-lg"
							role="group" aria-label="...">
							<div class="btn-group" role="group">
								<button type="button" id="stars" class="btn btn-success"
									href="#tab1" data-toggle="tab">
									<span class="fa fa-cogs fa-5x" aria-hidden="true"></span>
									<div class="hidden-xs">Reservation</div>
								</button>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="tab-content">
								<div class="tab-pane fade in active header" id="tab1">

									<h2 class="text-center text-info">Gerer Les Reservation</h2>


									<!-- Search -->
									<div id="formsearch" class="panel panel-default">
										
									</div>
								</div>

								<!--  ./Search -->
								<table class="table table-striped custab">
									<thead>
										<tr>
											<th>Num</th>
											<th>Capacité</th>
											<th>Number Equipment</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tr>
										<td>1</td>
										<td>30</td>
										<td>20 <span class="fa fa-info-circle"></span></td>
										<td class="text-center"><a href="#"
											class="btn btn-info btn-sm"> <span
												class="fa fa-plus-square-o"></span>&nbsp;&nbsp; Show
												Schedule
										</a></td>
									</tr>
								</table>
								<div id="schedule">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th scope="col">Days/ Hours</th>
												<th scope="col">08:30 -- 10:00</th>
												<th scope="col">10:00 -- 11:30</th>
												<th scope="col">11:30 -- 13:00</th>
												<th scope="col">13:00 -- 14:30</th>
												<th scope="col">14:30 -- 16:00</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">Sunday</th>
												<td>Mark</td>
												<td>Otto</td>
												<td>@mdo</td>
												<td>Otto</td>
												<td>@mdo</td>

											</tr>
											<tr>
												<th scope="row">Monday</th>
												<td>Mark</td>
												<td>Otto</td>
												<td>Otto</td>
												<td>@mdo</td>
												<td>@TwBootstrap</td>
											</tr>
											<tr>
												<th scope="row">Tuesday</th>
												<td>Jacob</td>
												<td>Thornton</td>
												<td>@fat</td>
												<td>Otto</td>
												<td>@mdo</td>

											</tr>
											<tr>
												<th scope="row">Thursday</th>
												<td>Otto</td>
												<td>@mdo</td>
												<td>Jacob</td>
												<td>Thornton</td>
												<td>@fat</td>
											</tr>
										</tbody>
									</table>
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

	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/CTRLGererReservation.js"></script>
	<script src="js/main.js"></script>
</body>
</html>
