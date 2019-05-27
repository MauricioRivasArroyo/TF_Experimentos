package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;


import dao.ClienteDAO;
import model.Cliente;


@RunWith(MockitoJUnitRunner.class)

public class ClienteTest {
	Cliente cliente;	
	ClienteDAO clienteDAO;	
	WebDriver driver;
	
	@Before
	public void setup() throws MalformedURLException, UnknownHostException{
		System.setProperty("webdriver.opera.driver", "C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (11)\\operadriver_win64\\operadriver.exe");
		
	}
	
	@Mock
	private ClienteDAO clienteDAOMock;
	@Mock
	private Cliente clienteMock;
	
	@Test
	public void testAuthenticationFailureWhenProvidingBadCredentials(){	  
		driver = new OperaDriver();
		driver.get("http://localhost:8080/CRUD-Clientes/vistas/registrar.jsp");

	    driver.findElement(By.name("registrar")).click();

	    assertTrue(driver.getCurrentUrl().endsWith("http://localhost:8080/CRUD-Clientes/adminCliente?action=register"));
	}

	@Test
	public void testAuthenticationSuccessWhenProvidingCorrectCredentials(){
		driver = new OperaDriver();
		driver.get("http://localhost:8080/CRUD-Clientes/vistas/registrar.jsp");

		   driver.findElement(By.name("cedula")).sendKeys("cedula1");
		    driver.findElement(By.name("nombre")).sendKeys("nombre");
		    driver.findElement(By.id("m")).click();
		    driver.findElement(By.name("registrar")).click();

	    assertTrue(driver.getCurrentUrl().endsWith("http://localhost:8080/CRUD-Clientes/adminCliente?action=register"));
	}
	
	/*@Test
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
	}*/
	
	
	/*@Test
	public void actualizarNombreConNumeros() {
		try {
			 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String jdbcUsername = "root";
				String jdbcPassword = "";
			    System.out.println("Metodo actualizar"); 
			    String val = "nombre1";
			    clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);	
				  Assert.assertFalse(clienteDAO.actualizar(cliente));

		}catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail("Fallo la prueba:" + e.getMessage());

		}
	}*/
	
/*	@Test
	public void actualizarExitoso() {
		try {
			 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String jdbcUsername = "root";
				String jdbcPassword = "";
			    System.out.println("Metodo actualizar"); 
			    Cliente clienteMock = Mockito.mock(Cliente.class);
			    ClienteDAO clienteDAOMock = Mockito.mock(ClienteDAO.class);
				 clienteMock = new Cliente(0,"65744", "nombre", "apellido","masculino","normal","correo1");				 
				 clienteDAOMock = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
				 when(clienteDAOMock.actualizar(clienteMock)).thenReturn(true);	
				 clienteMock.setNombre("nuevo");
				  Assert.assertTrue(clienteDAOMock.actualizar(clienteMock));
		}catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail("Fallo la prueba:" + e.getMessage());

		}
	}
	*//*
	@Test
	public void registroExitoso() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		 clienteMock = new Cliente();
		 clienteMock.setCedula("9899");
		 clienteMock.setNombre("nombre");
		 clienteMock.setApellido("apellido");
		 clienteMock.setGenero("femenino");
		 clienteMock.setCategoria("premium");
		 clienteMock.setCorreo("correo2");
		 clienteDAOMock = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);	
		 when(clienteDAOMock.insertar2(any(Cliente.class))).thenReturn(clienteMock);
		 
		  Assert.assertTrue(true);
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }*/
	
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
