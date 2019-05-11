<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar Cliente</title>
</head>
<body>
    <h2>Actualizar Cliente</h2>
	<form action="adminCliente?action=editar" method="post" >
		<table class="table">	
			<tr>
				<th scope="row"><label>Id</label></th>
				<td><input type="text" name="id" value="<c:out value="${cliente.id}"></c:out>"></td>
			</tr>		
			<tr>
				<th scope="row"><label>Cédula</label></th>
				<td><input type="text" name="cedula" value="<c:out value="${cliente.cedula}"></c:out>"></td>
			</tr>
			<tr>
				<th scope="row"><label>Nombre</label></th>
				<td><input type="text" name="nombre" value="<c:out value="${cliente.nombre}"></c:out>" ></td>
			</tr>
			<tr>
				<th scope="row"><label>Apellido</label></th>
				<td><input type="text" name="apellido" value="<c:out value="${cliente.apellido}"></c:out>" ></td>
			</tr>
			<tr>
				<th scope="row"><label>Genero</label></th>
				<td><input type="radio" name="genero" value="masculino"/>M
				<input type="radio" name="genero" value="femenino"/>F</td>
			</tr>
			<tr>
				<th scope="row"><label>Categoria</label></th>
				<td><input type="checkbox" name="categoria" value="premium"/>Premium
				<input type="hidden" name="categoria" value="normal" />
			</td>
			</tr>
			<tr>
				<th scope="row"><label>Correo</label></th>
				<td><input type="text" name="correo" value="<c:out value="${cliente.correo}"></c:out>" ></td>
			</tr>
		</table>
	
		<table border="0" align="center">
		<tr>
		<td><button type="submit" class="btn btn-secondary">Guardar</button></td>	
		</tr>
		</table>	</form>

</body>
</html>