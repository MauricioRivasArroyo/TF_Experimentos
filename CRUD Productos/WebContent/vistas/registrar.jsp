<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Producto</title>
</head>
<body>
	<h2>Registrar Producto</h2>
	<form action="adminProducto?action=register" method="post">
		<table align="center" class="table">		
		<tr>
			<th scope="row"><a>Nombre:</a></th>		
			<td><input type="text" name="nombre"/></td>	
		</tr>
		<tr>
			<th scope="row"><a>Categoria:</a></th>		
			<td><select class="selectpicker" name="categoria">
			<c:forEach var="cat" items="${lista}">
			<option value="<c:out value="${cat.id}"></c:out>"><c:out value="${cat.nombre}"></c:out></option>
			</c:forEach>
			</select></td>	
		</tr>		
		
		</table>
	<br>
	<table border="0" align="center">
		<tr>
		<td><input type="submit" value="Agregar" name="agregar"></td>	
		</tr>
		</table>
	</form>
</body>
</html>