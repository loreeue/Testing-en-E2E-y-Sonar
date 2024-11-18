package es.codeurjc.ais.nitflex.Practica2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.ais.nitflex.Application;

@SpringBootTest(
		classes = Application.class, 
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BugTest {
	@LocalServerPort
    int port;

	WebDriver driver;
	
	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
	}
	
	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}

	@Test
	public void detectBugTest() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/");
		
		driver.findElement(By.id("create-film")).click(); //Click "New Film"
		
		//Fill in the fields of a new film
		driver.findElement(By.name("title")).sendKeys("101 dalmatas");
		driver.findElement(By.name("releaseYear")).sendKeys("1961");
		driver.findElement(By.name("url")).sendKeys("https://www.tebeosfera.com/T3content/img/T3_series/a/s/101_dalmatas.jpeg");
		driver.findElement(By.name("synopsis")).sendKeys("Los dalmatas Pongo y Perdita son una feliz pareja canina que vive rodeada de sus cachorros y con sus amos, Roger y Anita. "
				+ "pero su felicidad esta amenazada por Cruella De Ville, una perfida mujer que adora los abrigos de pieles.");

		driver.findElement(By.id("Save")).click(); //Click "Save"
		String urlActual = driver.getCurrentUrl();

		driver.findElement(By.id("edit-film")).click(); //Click "Edit"
		
		driver.findElement(By.id("cancel")).click(); //Click "Cancel"
		String urlPosterior = driver.getCurrentUrl();
		
		String titleNuevo = driver.findElement(By.id("film-title")).getText();
		String synopsisNuevo = driver.findElement(By.id("film-synopsis")).getText();
		
		assertThat(titleNuevo).isEqualTo("101 dalmatas");
		assertThat(synopsisNuevo).isEqualTo("Los dalmatas Pongo y Perdita son una feliz pareja canina que vive rodeada de sus cachorros y con sus amos, Roger y Anita. "
				+ "pero su felicidad esta amenazada por Cruella De Ville, una perfida mujer que adora los abrigos de pieles.");
		assertThat(urlActual).isEqualTo(urlPosterior);
	}

}

