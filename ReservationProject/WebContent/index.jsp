<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
	font-family: "Ubuntu" !important;
	padding:  0;
	margin: 0;
}

div#wrapper {
	width: 100%;
	height: 800px;
	background: -webkit-gradient(linear, 100% 0%, 0% 100%, from(#2D3AED),
		to(#69D1A7)) no-repeat;
}
div#header p {
	font-size: 3em;
	color: white;
	text-align: center;
	text-shadow: 0 0px 5px #010101;
}

div#auth {
	width: 300px;
	min-height: 50px;
	margin: 70px auto;
	border: 3px solid #f3f3f3;
	border-radius: 10px;
	border: 3px solid #f5f5f5;
}

div#auth input, div#auth button {
	margin: 5px auto;
	padding: 8px;
	border: 3px solid #f3f3f3;
	border-radius: 10px;
	display: block;
}

</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<p>Reservation des Salles</p>
		</div>
		<div id="auth">
			<form role="form" onsubmit="return false;">
				<input type="text" name="usr" id="usr" placeholder="username ..." />
				<input type="password" name="pwd" id="pwd"
					placeholder="password ..." />
				<button type="submit" id="loginbutton">Login</button>
				<span id="result"></span>
			</form>
		</div>

	</div>

	<!-- jQuery -->
	<script src="js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript">
$("#loginbutton").click(function() {
	var username = document.getElementById("usr").value;
	var password = document.getElementById("pwd").value;
	$.ajax({
			url : 'AuthentificationCtrl',
			data : {
				username : username,
				password : password
			},
			type : 'post',
			cache : false,
			success : function(data) {
				console.log(data);
				if (data == ('bloque')) {
					$('#result')
							.text('le compte et bloque');
				} else if (data == ('error')) {
					$('#result').text(
							'le compte n exite pas');
				} else {
					window.location.href = 'http://localhost:8085/ReservationProject/'
							+ data;
				}
			},
			error : function() {
			
				console.log('error!!');
			}
		});
});

</script>
</body>
</html>