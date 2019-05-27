package test;

import org.junit.Assert;
import org.junit.Test;
import dao.ProductoDAO;
import model.Producto;

public class ProductoTest {
	Producto producto;
	ProductoDAO productoDAO;
	
	@Test
	public void registroExitoso() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		  producto = new Producto(0,"nombre",3,"5432");
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertTrue(productoDAO.insertar(producto));
		  productoDAO.eliminar(producto);
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
		  producto = new Producto(0,"",3,"5432");
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertFalse(productoDAO.insertar(producto));
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
	@Test
	public void registroConDatosInvalidos() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo insertar"); 
		  producto = new Producto(0,"nombre1",3,"5432");
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);				 
		  Assert.assertFalse(productoDAO.insertar(producto));
	  }	  catch (Exception e){
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
		  producto = new Producto(0,"nombre",3,"12345");	      
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword); 
		 productoDAO.insertar(producto);
		 producto.setCategoria(4);
		  Assert.assertTrue(productoDAO.actualizar(producto));
			productoDAO.eliminar(producto);
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
	@Test
	public void actualizarConDatosInvalidos() {
	  try {
		 String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		 String jdbcUsername = "root";
		 String jdbcPassword = "";
		 System.out.println("Metodo actualizar"); 
		  producto = new Producto(0,"nombre",3,"87879");
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);
		 productoDAO.insertar(producto);
		 producto.setNombre("");
		  Assert.assertFalse(productoDAO.actualizar(producto));	
		  productoDAO.eliminar(producto);
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
		  producto = new Producto(0,"nombre",3,"12345");		  
		 productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);
		 productoDAO.insertar(producto);				 
		  Assert.assertTrue(productoDAO.eliminar(producto));	
	  }	  catch (Exception e){
		  e.printStackTrace();
		  Assert.fail("Fallo la prueba:" + e.getMessage());
	  }
  }
}
