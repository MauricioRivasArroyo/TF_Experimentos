<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body>
<div class="container col-lg-3">
<form action="adminController?action=login" method="post">
	<div class="form-group text-center">
	<h2>Login</h2>
	</div>
	<div class="form-group">
		<label>User:</label>
		<input class="form-control" type="text" name="nombre" placeholder="Username">
	</div>
	<div class="form-group">
		<label>Password:</label>
		<input class="form-control" type="text" name="contraseña" placeholder="Password">
	</div>
		<input class="btn btn-danger btn-block" type="submit" name="login" value="Ingresar">
</form>
<br>
<table align="center" class="table">
		<tr>
			<td align="center"><a  name="registrarse" href="adminController?action=nuevo_usuario">Registrarse</a></td>			
		</tr>
	</table>
</div>
</body>
</html>