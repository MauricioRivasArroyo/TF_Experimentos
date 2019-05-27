<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar productos</title>
</head>
<body>
	<h2>Lista  Productos</h2>
	<table>
		<tr>
			<td><a href="adminController?action=menu_productos" >Regresar</a> </td>
		</tr>
	</table>
	
	<table width="100%" class="table">
		<thead>
		<tr>
		 <th scope="col"> ID</th>	
		 <th scope="col"> NOMBRE</th>
		 <th scope="col">CATEGORIA</th>
 		 <th scope="col">CODIGO</th>		 
		 <th scope="col" colspan=2>ACCIONES</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="producto" items="${lista_productos}">
			<tr>
				<td><c:out value="${producto.id}"/></td>
				<td><c:out value="${producto.nombre}"/></td>
				<td><c:out value="${producto.categoria}"/></td>
				<td><c:out value="${producto.codigo}"/></td>				
				<td><a type="button" class="btn btn-secondary" href="adminController?action=showedit_producto&id=<c:out value="${producto.id}" />">Editar</a>
				<a type="button" class="btn btn-secondary" href="adminController?action=eliminar_producto&id=<c:out value="${producto.id}" />">Eliminar</a> </td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
</body>
</html>