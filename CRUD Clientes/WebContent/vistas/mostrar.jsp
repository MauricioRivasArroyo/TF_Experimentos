<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar clientes</title>
</head>
<body>
	<h1>Lista  Clientes</h1>
	<table>
		<tr>
			<td><a href="adminCliente?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> CEDULA</td>
		 <td> NOMBRE</td>
		 <td>APELLIDO</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="cliente" items="${lista}">
			<tr>
				<td><c:out value="${cliente.id}"/></td>
				<td><c:out value="${cliente.cedula}"/></td>
				<td><c:out value="${cliente.nombre}"/></td>
				<td><c:out value="${cliente.apellido}"/></td>
				<td><a href="adminCliente?action=showedit&id=<c:out value="${cliente.id}" />">Editar</a></td>
				<td><a href="adminCliente?action=eliminar&id=<c:out value="${cliente.id}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>