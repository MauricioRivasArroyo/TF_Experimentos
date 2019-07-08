<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Cliente</title>
</head>
<body>
	<h2>Registrar Cliente</h2>
	<table>
		<tr>
			<td><a href="adminController?action=menu_clientes" >Regresar</a> </td>
		</tr>
	</table>
	<form action="adminController?action=registrar_cliente" method="post">
		<table align="center" class="table">
		<tr>
			<th scope="row"><a>Cedula:</a></th>		
			<td><input type="text" name="cedula"/></td>	
		</tr>
		<tr>
			<th scope="row"><a>Nombre:</a></th>		
			<td><input type="text" name="nombre"/></td>	
		</tr>
		<tr>
			<th scope="row"><a>Apellido:</a></th>		
			<td><input type="text" name="apellido"/></td>	
		</tr>		
		<tr>
			<th scope="row"><a>Genero:</a></th>		
			<td><input type="radio"  id ="m"name="genero" value="masculino"/>M
			<input type="radio" id="f" name="genero" value="femenino"/>F</td>	
		</tr>
		<tr>
			<th scope="row"><a>Categoria:</a></th>		
			<td><input type="checkbox" name="categoria" id="p" value="premium"/>Premium
			<input type="hidden" name="categoria" value="normal" />
			</td>	
		</tr>				
		<tr>
			<th scope="row"><a>Correo:</a></th>		
			<td><input type="text" name="correo"/></td><td><label>*Opcional</label></td>
		</tr>
		</table>
	<br>
	<table border="0" align="center">
		<tr>
		<td><button type="submit" name="registrarCliente" class="btn btn-secondary">Agregar</button></td>	
		</tr>
		</table>
	</form>
</body>
</html>