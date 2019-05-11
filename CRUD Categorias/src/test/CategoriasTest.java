package test;

import org.junit.Assert;
import org.junit.Test;

import dao.CategoriaDAO;
import model.Categoria;

public class CategoriasTest {
	private CategoriaDAO categoriaDAO;
	private Categoria categoria;
	
	public CategoriasTest() {
	 	String jdbcURL = "jdbc:mysql://localhost:3306/ventas?user=root&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String jdbcUsername = "root";
		String jdbcPassword = "root";
	   try {
		    categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);		   
	   }
	   catch(Exception e) {
		   e.printStackTrace();
		   System.out.println(e.getMessage());
	   }
	}
	
	//Test Suite: Insertar Categoria
	
	@Test
	public void insertarExitoso() {
		try {
			System.out.println("Categoria - Metodo insertarExitoso");
		    categoria = new Categoria(0,"Ropa") ;	
			Assert.assertTrue(categoriaDAO.insertar(categoria));
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nomre UNIQUE
			categoriaDAO.eliminarPorNombre(categoria);
		}
		catch (Exception e) {
				  e.printStackTrace();
				  Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
	
	@Test
	public void insertarNombreVacio() {
		try {
			System.out.println("Categoria - Metodo insertarNombreVacio");
		    categoria = new Categoria(0,"") ;	
			Assert.assertFalse(categoriaDAO.insertar(categoria));
		}
		catch (Exception e) {
				  e.printStackTrace();
				  Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
	
	@Test
	public void insertarNombreNumerico() {
		try {
			System.out.println("Categoria - Metodo insertarNombreNumerico");
		    categoria = new Categoria(0,"123456") ;	
			Assert.assertFalse(categoriaDAO.insertar(categoria));
		}
		catch (Exception e) {
				  e.printStackTrace();
				  Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
}
