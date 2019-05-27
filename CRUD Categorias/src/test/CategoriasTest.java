package test;

import java.util.List;

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
		String jdbcPassword = "";
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
	
	//Test Suite: Actualizar Categoria
	
	@Test
	public void actualizarExitoso() {
		try {
			System.out.println("Categoria - Metodo actualizarExitoso");
		    categoria = new Categoria(0,"CategoriaCreadaEXITOSO");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    categoria = Categoria.builder().setId(categoria.getId()).setNombre("Mochila").build();
			Assert.assertTrue(categoriaDAO.actualizar(categoria));
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
	
	@Test
	public void actualizarNombreVacio() {
		try {
			System.out.println("Categoria - Metodo actualizarNombreVacio");
		    categoria = new Categoria(0,"CategoriaCreadaVACIO");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    categoria = Categoria.builder().setId(categoria.getId()).setNombre("").build();
			Assert.assertFalse(categoriaDAO.actualizar(categoria));
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
	
	@Test
	public void actualizarExitosoYObservarCambios() {
		try {
			System.out.println("Categoria - Metodo actualizarExitosoYObservarCambios");
		    categoria = new Categoria(0,"CategoriaCreadaEXITOSOYCAMBIOS");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    categoria = Categoria.builder().setId(categoria.getId()).setNombre("Ropa").build();
		    Categoria categoriaRopa = categoriaDAO.obtenerPorNombre(categoria);
			Assert.assertNotNull(categoriaRopa);
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	} 
	
	@Test
	public void actualizarNombreNumerico() {
		try {
			System.out.println("Categoria - Metodo actualizarNombreNumerico");
		    categoria = new Categoria(0,"CategoriaCreadaNUMERICO");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    categoria = Categoria.builder().setId(categoria.getId()).setNombre("123456").build();
			Assert.assertFalse(categoriaDAO.actualizar(categoria));
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba:" + e.getMessage());
		}
	}
	
	//Test Suite: Visualizar listado de categorias
	
	@Test
	public void visualizarListadoVacio() {
		List<Categoria> categorias;
		try {
			System.out.println("Categoria - Metodo visualizarListadoVacio");
			List<Categoria> categoriasCopia = categoriaDAO.listarCategorias();
			categoriaDAO.eliminarTodos();
			categorias = categoriaDAO.listarCategorias();
			Assert.assertTrue(categorias.isEmpty());
			for (Categoria categoria : categoriasCopia) {
				//Necesita ser optimizado para no estar abriendo la conexion por cada insercion
				//Posiblemente necesite tener un metodo que reciba una lista de parametro y trabaje los elementos con statements
				categoriaDAO.insertar(categoria);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba: " + e.getMessage());
		}
	}
	
	@Test
	public void visualizarCategoriaModificada() {
		List<Categoria> categorias;
		try {
			System.out.println("Categoria - Metodo visualizarCategoriaModificada");

		    categoria = new Categoria(0,"CategoriaCreadaModificadaComprobada");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    categoria = Categoria.builder().setId(categoria.getId()).setNombre("Electrodomesticos").build();
		    categoriaDAO.actualizar(categoria);
		    Categoria categoriaElectrodomesticos = categoriaDAO.obtenerPorNombre(categoria);
			Assert.assertEquals(categoriaElectrodomesticos.getNombre(), "Electrodomesticos");
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba: " + e.getMessage());
		}
	}
	
	@Test
	public void visualizarCategoriaRegistrada() {
		List<Categoria> categorias;
		try {
			System.out.println("Categoria - Metodo visualizarCategoriaRegistrada");

		    categoria = new Categoria(0,"Herramientas");
		    categoriaDAO.insertar(categoria);
		    categoria = categoriaDAO.obtenerPorNombre(categoria);
		    Categoria categoriaHerramientas = categoriaDAO.obtenerPorNombre(categoria);
			Assert.assertEquals(categoriaHerramientas.getNombre(), "Herramientas");
			//Para que la prueba sea repetible, eliminamos la categoria insertada con columna de nombre UNIQUE
			categoriaDAO.eliminar(categoria);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Fallo la prueba: " + e.getMessage());
		}
	}
	
	//Test Suite: Eliminar Categoria
	
	@Test
	public void eliminarCategoria() {
		try {
			System.out.println("Categoria - Metodo eliminarCategoria");
			
			List<Categoria> categorias = categoriaDAO.listarCategorias();
			if(categorias.size()>0) {
			Categoria ultimaCategoria = categorias.get(categorias.size()-1);
			
			Assert.assertTrue(categoriaDAO.eliminar(ultimaCategoria));
			Assert.assertNull(categoriaDAO.obtenerPorId(ultimaCategoria.getId()));
			}else {
				Assert.assertTrue(true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}
}
