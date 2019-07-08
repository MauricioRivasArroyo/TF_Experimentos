package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.*;

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

	@Before
	public void setup() throws MalformedURLException, UnknownHostException{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (5)\\chromedriver.exe");
		driver = new ChromeDriver();
		writeFile = new WriteExcelFile();
		readFile = new ReadExcelFile();
		driver.get("http://localhost:8080/CRUDS-Ventas/");
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
}
