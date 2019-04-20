<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar Cliente</title>
</head>
<body>
    <h1>Actualizar Cliente</h1>
	<form action="adminCliente?action=editar" method="post" >
		<table border="1" align="center">			
			<tr>
				<td><label>Cédula</label></td>
				<td><input type="text" name="cedula" value="${cliente.cedula}"></td>
			</tr>
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="nombre" value="${cliente.nombre}" ></td>
			</tr>
			<tr>
				<td><label>Apellido</label></td>
				<td><input type="text" name="apellido" value="${cliente.apellido}" ></td>
			</tr>
		</table>
	
		<input type="submit" name="registrar" value="Guardar"> 
	</form>

</body>
</html>