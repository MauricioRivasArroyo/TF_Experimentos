<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Categoria</title>
</head>
<body>
	<h2>Registrar Categoria</h2>
	<table>
		<tr>
			<td><a href="adminController?action=menu_categorias" >Regresar</a> </td>
		</tr>
	</table>
	<form action="adminController?action=registrar_categoria" method="post">
		<table align="center" class="table">
		<tr>
			<th scope="row"><a>Nombre:</a></th>		
			<td><input type="text" name="nombre"/></td>	
		</tr>		
		</table>
	<br>
	<table border="0" align="center">
		<tr>
		<td><button type="submit" class="btn btn-secondary">Agregar</button></td>	
		</tr>
		</table>
	</form>
</body>
</html>