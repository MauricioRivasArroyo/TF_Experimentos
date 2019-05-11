<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar Producto</title>
</head>
<body>
    <h2>Actualizar Producto</h2>
	<form action="adminProducto?action=editar" method="post" >
		<table class="table">	
			<tr>
				<th scope="row"><label>Id</label></th>
				<td><input type="text" name="id" value="<c:out value="${producto.id}"></c:out>"></td>
			</tr>				
			<tr>
				<th scope="row"><label>Nombre</label></th>
				<td><input type="text" name="nombre" value="<c:out value="${producto.nombre}"></c:out>" ></td>
			</tr>
			<tr>
				<th scope="row"><label>Categoria</label></th>
				<td><select  name="categoria">
			<c:forEach var="cat" items="${lista}">
			<option value="<c:out value="${cat.id}"></c:out>"><c:out value="${cat.nombre}"></c:out></option>
			</c:forEach>
			</select></td>
			<tr>
				<th scope="row"><label>Codigo</label></th>
				<td><input type="text" name="codigo" value="<c:out value="${producto.codigo}"></c:out>" ></td>
			</tr>
			</tr>
		</table>
	
	<table border="0" align="center">
		<tr>
		<td><button type="submit" class="btn btn-secondary">Guardar</button></td>	
		</tr>
		</table>
			</form>

</body>
</html>