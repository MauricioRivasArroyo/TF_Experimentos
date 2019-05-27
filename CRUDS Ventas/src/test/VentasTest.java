package test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.*;

import dao.VentasDAO;
import model.*;

public class VentasTest {
	private WebDriver driver;
	
	@Before
	public void setup() throws MalformedURLException, UnknownHostException{
		System.setProperty("webdriver.opera.driver", "C:\\Users\\MAURICIO\\Desktop\\Nueva carpeta (11)\\operadriver_win64\\operadriver.exe");
		driver = new OperaDriver();
		driver.get("http://localhost:8080/CRUDS-Ventas/");
	}

	@Test
	public void testAuthenticationFailureWhenProvidingBadCredentials(){
	    driver.findElement(By.name("nombre")).sendKeys("fakeuser");
	    driver.findElement(By.name("contraseña")).sendKeys("fakepassword");     
	    driver.findElement(By.name("login")).click();
	    
	    assertTrue(driver.getTitle().endsWith("Index"));
	}

	@Test
	public void testAuthenticationSuccessWhenProvidingCorrectCredentials(){
		   driver.findElement(By.name("nombre")).sendKeys("user1");
		    driver.findElement(By.name("contraseña")).sendKeys("pass1");     
		    driver.findElement(By.name("login")).click();

	    assertTrue(driver.getTitle().endsWith("Principal"));
	}
}
