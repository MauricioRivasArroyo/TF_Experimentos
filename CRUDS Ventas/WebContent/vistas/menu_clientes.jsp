<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clientes</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
	<div class="dropdown">
  <a class="nav-link dropdown-toggle" style="color:white" data-toggle="dropdown" >
    Sign out
  </a>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" >${user}</a>
    <a class="dropdown-item" href="adminController?action=index">Salir</a>
  </div>
</div>
</nav><br>
	<h2>Administrar Clientes</h2>
	<table>
		<tr>
			<td><a href="adminController?action=principal" >Regresar</a> </td>
		</tr>
	</table>
	<table align="center" class="table">
		<tr>
			<td align="center"><a name="NuevoCliente" href="adminController?action=nuevo_cliente">Nuevo</a></td>			
		</tr>
		<tr>
			<td align="center"><a name="MostrarClientes" href="adminController?action=mostrar_clientes">Mostrar</a></td>
		</tr>
	</table>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>