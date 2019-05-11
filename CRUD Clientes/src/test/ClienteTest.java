package test;

import org.junit.Assert;
import org.junit.Test;
import dao.ClienteDAO;
import model.Cliente;

public class ClienteTest {
	Cliente cliente;	
	ClienteDAO clienteDAO;
	@Test
	public void actualizarSinModificaciones() {
		try {
			 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String jdbcUsername = "root";
				String jdbcPassword = "";
			    System.out.println("Metodo actualizar"); 
				 cliente = new Cliente(0,"65744", "nombre", "apellido","masculino","premium","correo1") ;
				 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);	
				  Assert.assertFalse(clienteDAO.actualizar(cliente));

		}catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail("Fallo la prueba:" + e.getMessage());

		}
	}
	
	@Test
	public void actualizarNombreConNumeros() {
		try {
			 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String jdbcUsername = "root";
				String jdbcPassword = "";
			    System.out.println("Metodo actualizar"); 
				 cliente = new Cliente(0,"65744", "nombre1", "apellido1","masculino","premium","correo1") ;
				 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);	
				  Assert.assertFalse(clienteDAO.actualizar(cliente));

		}catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail("Fallo la prueba:" + e.getMessage());

		}
	}
	
	@Test
	public void actualizarExitoso() {
		try {
			 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String jdbcUsername = "root";
				String jdbcPassword = "";
			    System.out.println("Metodo actualizar"); 
				 cliente = new Cliente(0,"65744", "nombre", "apellido","masculino","normal","correo1") ;
				 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);	
				  Assert.assertTrue(clienteDAO.actualizar(cliente));
				  cliente.setCategoria("premium");
				  clienteDAO.actualizar(cliente);
		}catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail("Fallo la prueba:" + e.getMessage());

		}
	}
	
	@Test
	public void registroExitoso() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		 cliente = new Cliente(0,"885657", "nombre", "apellido","masculino","premium","correo1");
		 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertTrue(clienteDAO.insertar(cliente));
		  clienteDAO.eliminarPorCedula(cliente);
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
	@Test
	public void registroSinCamposObligatorios() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		 cliente = new Cliente(0,"885657", "", "apellido","masculino","premium","correo1");
		 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertFalse(clienteDAO.insertar(cliente));
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
	@Test
	public void registroConValoresInvalidos() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		 cliente = new Cliente(0,"885657", "nombre1", "apellido1","masculino","premium","correo1");
		 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertFalse(clienteDAO.insertar(cliente));
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
	@Test
	public void eliminarExitoso() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo eliminar"); 
		 cliente = new Cliente(0,"65744", "nombre", "apellido","masculino","premium","correo1") ;
		 clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);			 
		  Assert.assertTrue(clienteDAO.eliminarPorCedula(cliente));
		  clienteDAO.insertar(cliente);
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
  
}
