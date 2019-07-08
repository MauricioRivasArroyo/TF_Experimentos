package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.*;

import dao.VentasDAO;
import model.*;

public class VentasTest {
	private WebDriver driver;
	private WriteExcelFile writeFile;
	private ReadExcelFile readFile;
	private By inputUserLogin = By.name("nombre");   
	private By inputPassLogin = By.name("contraseña");	
	private By inputUserRegister = By.name("username");   
	private By inputPassRegister = By.name("password");	
	private By btnIngresarLogin = By.name("login");
	private By btnRegistrarseLogin = By.name("registrarse");
	private By btnRegistrarseRegister = By.name("registrar");
	private By btnCrudClientes= By.name("btnCrudClientes");
	private By btnNuevoCliente= By.name("NuevoCliente");
	private By btnGuardarCliente= By.name("GuardarCliente");
	private By btnMostrarClientes= By.name("MostrarClientes");
	private By btnRegistrarCliente= By.name("registrarCliente");
	private By inputCedulaCliente = By.name("cedula");
	private By inputNombreCliente = By.name("nombre");   
	private By inputApellidoCliente = By.name("apellido");
	private By RGeneroMasculinoCliente = By.id("m");
	private By RGeneroFemeninoCliente = By.id("f");
	private By CPremiumCliente = By.id("p");
	private VentasDAO ventasDAO;

	@Before
	public void setup() throws MalformedURLException, UnknownHostException, SQLException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (5)\\chromedriver.exe");
		driver = new ChromeDriver();
		writeFile = new WriteExcelFile();
		readFile = new ReadExcelFile();
		driver.get("http://localhost:8080/CRUDS-Ventas/");
		ventasDAO = new VentasDAO();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	//Pruebas del Login
	
	@Test
	public void RegistroLogin() throws IOException {
		String filepath ="C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (7)\\TF_Experimentos\\Pool.xlsx";  
		int pruebasResueltas = 0;
		
		for(int i =0; i<3;i++) {
			driver.get("http://localhost:8080/CRUDS-Ventas/");
			
			driver.findElement(btnRegistrarseLogin).click();

			String user = readFile.getCellValue(filepath, "Hoja1", i+4, 1);      
			String clave = readFile.getCellValue(filepath, "Hoja1", i+4, 2); 	
			
			driver.findElement(inputUserRegister).sendKeys(user);
			driver.findElement(inputPassRegister).sendKeys(clave); 
			
			driver.findElement(btnRegistrarseRegister).click();
			
			if(driver.getTitle().endsWith("Registrar Usuario")) {
				writeFile.writeCellValue(filepath, "Hoja1", i+4, 3, "no se registró");
			}else {
				writeFile.writeCellValue(filepath, "Hoja1", i+4, 3, "se registró");
	
			}
		}
		
		for(int i = 0; i<3;i++) {	
			String resultado = readFile.getCellValue(filepath, "Hoja1", i+4, 3);     
			if((i+1) % 2 == 0 ) {
				if(resultado.equals("se registró"))
					pruebasResueltas += 1;
			}else if( resultado.equals("no se registró")) {
				pruebasResueltas += 1;
			}
			
		}
		
		 assertTrue(pruebasResueltas == 3);
		
	}
	
	@Test
	public void Login() throws IOException {
		String filepath ="C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (7)\\TF_Experimentos\\Pool.xlsx";  
		int pruebasResueltas = 0;
		
		for(int i =0; i<2;i++) {
			driver.get("http://localhost:8080/CRUDS-Ventas/");

			String user = readFile.getCellValue(filepath, "Hoja1", i+10, 1);      
			String clave = readFile.getCellValue(filepath, "Hoja1", i+10, 2); 	
		
			driver.findElement(inputUserLogin).sendKeys(user);
			driver.findElement(inputPassLogin).sendKeys(clave); 
			
			driver.findElement(btnIngresarLogin).click();
			
			
			if(driver.getTitle().endsWith("Index")) {
				writeFile.writeCellValue(filepath, "Hoja1", i+10, 3, "no ingresó");
			}else {
				writeFile.writeCellValue(filepath, "Hoja1", i+10, 3, "ingresó");
	
			}
			
		}
		for(int i = 0; i<2;i++) {	
			String resultado = readFile.getCellValue(filepath, "Hoja1", i+10, 3);     
			if((i+1) % 2 == 0 ) {
				if(resultado.equals("ingresó"))
					pruebasResueltas += 1;
			}else if( resultado.equals("no ingresó")) {
				pruebasResueltas += 1;
			}
			
		}
		 assertTrue(pruebasResueltas == 2);		
	}
	
	//Pruebas CRUD
	
	@Test
	public void InsertarCliente() throws IOException {
		String filepath ="C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (7)\\TF_Experimentos\\Pool.xlsx";  
		int pruebasResueltas = 0;
		
		for(int i =0; i<5;i++) {
			driver.get("http://localhost:8080/CRUDS-Ventas/");
			
			driver.findElement(inputUserLogin).sendKeys("user1");
			driver.findElement(inputPassLogin).sendKeys("pass1");
			
			driver.findElement(btnIngresarLogin).click();
			driver.findElement(btnCrudClientes).click();
			driver.findElement(btnNuevoCliente).click();

			String cedula = readFile.getCellValue(filepath, "Hoja1", i+17, 1);      
			String nombre = readFile.getCellValue(filepath, "Hoja1", i+17, 2); 	
			String apellido = readFile.getCellValue(filepath, "Hoja1", i+17, 3); 	
			String genero = readFile.getCellValue(filepath, "Hoja1", i+17, 4); 	
			String categoria = readFile.getCellValue(filepath, "Hoja1", i+17, 5); 	
			
			driver.findElement(inputCedulaCliente).sendKeys(cedula);
			driver.findElement(inputNombreCliente).sendKeys(nombre);
			driver.findElement(inputApellidoCliente).sendKeys(apellido);
			
			if(genero.equals("masculino"))
			{
				driver.findElement(RGeneroMasculinoCliente).click();
			}else if(genero.equals("femenino")){
				driver.findElement(RGeneroFemeninoCliente).click();
			}
			 
			if(categoria.equals("premium"))
				driver.findElement(CPremiumCliente).click();

			driver.findElement(btnRegistrarCliente).click();
			
			if(driver.getTitle().endsWith("Registrar Cliente")) {
				writeFile.writeCellValue(filepath, "Hoja1", i+17, 6, "no se registró");
			}else {
				writeFile.writeCellValue(filepath, "Hoja1", i+17, 6, "se registró");
	
			}
		}
		
		for(int i = 0; i<5;i++) {	
			String resultado = readFile.getCellValue(filepath, "Hoja1", i+17, 6);     
			if((i+1) == 5 ) {
				if(resultado.equals("se registró"))
					pruebasResueltas += 1;
			}else if( resultado.equals("no se registró")) {
				pruebasResueltas += 1;
			}
			
		}
		
		 assertTrue(pruebasResueltas == 5);
		
	}
	@Test
	public void MostrarClientes() throws IOException {
		String filepath ="C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (7)\\TF_Experimentos\\Pool.xlsx";  
		int pruebasResueltas = 0;
		
			driver.get("http://localhost:8080/CRUDS-Ventas/");
			
			driver.findElement(inputUserLogin).sendKeys("user1");
			driver.findElement(inputPassLogin).sendKeys("pass1");
			
			driver.findElement(btnIngresarLogin).click();
			driver.findElement(btnCrudClientes).click();
			driver.findElement(btnMostrarClientes).click();
		for(int i =0; i<4;i++) {

			String cedula = readFile.getCellValue(filepath, "Hoja1", i+25, 1);    			
			 try{
				 driver.findElement(By.name(cedula));
					writeFile.writeCellValue(filepath, "Hoja1", i+25, 2, "se muestra");

		        }
		        catch(Exception e){
					writeFile.writeCellValue(filepath, "Hoja1", i+25, 2, "no se muestra");
		        }			
		}		
		
		for(int i = 0; i<4;i++) {	
			String resultado = readFile.getCellValue(filepath, "Hoja1", i+25, 2);     
			if((i+1) == 4 ) {
				if(resultado.equals("no se muestra"))
					pruebasResueltas += 1;
			}else if( resultado.equals("se muestra")) {
				pruebasResueltas += 1;
			}
			
		}
		
		 assertTrue(pruebasResueltas == 4);
		
	}
	@Test
	public void ActualizarEliminarClientes() throws IOException, SQLException {
		Cliente cliente = new Cliente(0,"cedula3","prueba","prueba","masculino","premium",null);
		String filepath ="C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (7)\\TF_Experimentos\\Pool.xlsx";  
		int pruebasResueltas = 0;
		
			driver.get("http://localhost:8080/CRUDS-Ventas/");
			
			driver.findElement(inputUserLogin).sendKeys("user1");
			driver.findElement(inputPassLogin).sendKeys("pass1");
			
			driver.findElement(btnIngresarLogin).click();
			driver.findElement(btnCrudClientes).click();
			driver.findElement(btnMostrarClientes).click();

			String cedulaCambiado = readFile.getCellValue(filepath, "Hoja1", 32, 1);    			
			String nombreCambiado = readFile.getCellValue(filepath, "Hoja1", 32, 2);    			
			String apellidoCambiado = readFile.getCellValue(filepath, "Hoja1", 32, 3);    			
			String generoCambiado = readFile.getCellValue(filepath, "Hoja1", 32, 4);    			
			
			 driver.findElement(By.id("cedula3")).click();
			 
			 driver.findElement(inputCedulaCliente).clear();
				driver.findElement(inputNombreCliente).clear();
				driver.findElement(inputApellidoCliente).clear();
				
			 driver.findElement(inputCedulaCliente).sendKeys(cedulaCambiado);
				driver.findElement(inputNombreCliente).sendKeys(nombreCambiado);
				driver.findElement(inputApellidoCliente).sendKeys(apellidoCambiado);
				
				if(generoCambiado.equals("masculino"))
				{
					driver.findElement(RGeneroMasculinoCliente).click();
				}else if(generoCambiado.equals("femenino")){
					driver.findElement(RGeneroFemeninoCliente).click();
				}
								
				 driver.findElement(btnGuardarCliente).click();
				 
				 try{
					 driver.findElement(By.name(cedulaCambiado));
						writeFile.writeCellValue(filepath, "Hoja1", 32, 5, "actualizó");

			        }
			        catch(Exception e){
						writeFile.writeCellValue(filepath, "Hoja1", 32, 5, "no actualizó");
			        }
				 
				 driver.findElement(By.id(nombreCambiado)).click();
				 driver.findElement(By.id("nombre")).click();

				 try{
					 driver.findElement(By.name(cedulaCambiado));
						writeFile.writeCellValue(filepath, "Hoja1", 32, 6, "no se eliminó");

			        }
			        catch(Exception e){
						writeFile.writeCellValue(filepath, "Hoja1", 32, 6, "se eliminó");
						}		
			
			String resultado = readFile.getCellValue(filepath, "Hoja1", 32, 5);     
			
			if(resultado.equals("actualizó"))
					pruebasResueltas += 1;
			
			resultado = readFile.getCellValue(filepath, "Hoja1", 32, 6);     
			 if( resultado.equals("se eliminó")) 
				pruebasResueltas += 1;
			ventasDAO.insertar_cliente(cliente);
		
		
		 assertTrue(pruebasResueltas == 2);
		
	}
	
}
