<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar categorias</title>
</head>
<body>
	<h1>Lista  Categorias</h1>
	<table>
		<tr>
			<td><a href="adminCategoria?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> NOMBRE</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="categoria" items="${lista}">
			<tr>
				<td><c:out value="${categoria.id}"/></td>
				<td><c:out value="${categoria.nombre}"/></td>
				<td><a href="adminCategoria?action=showedit&id=<c:out value="${categoria.id}" />">Editar</a></td>
				<td><a href="adminCategoria?action=eliminar&id=<c:out value="${categoria.id}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>