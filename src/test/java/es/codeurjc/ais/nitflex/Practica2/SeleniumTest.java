package es.codeurjc.ais.nitflex.Practica2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.ais.nitflex.Application;

@SpringBootTest(
		classes = Application.class, 
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SeleniumTest {
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

	private boolean findMovie(List<WebElement> titleList, List<WebElement> yearList, String expectedTitle, String expectedYear){
		for (int i = 0; i < titleList.size(); i++) {
			if (titleList.get(i).getText().equals(expectedTitle) && yearList.get(i).getText().equals(expectedYear)){
				return true;
			}
		}
		return false;
	}

	@Test
	public void createFilmTest() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/");

		//We make sure that the movie is not already created
		List<WebElement> elements1 = driver.findElements(By.className("film-title"));
		List<WebElement> elements2 = driver.findElements(By.className("year"));

		assertFalse(findMovie(elements1,elements2,"Hush","2016"));

		driver.findElement(By.id("create-film")).click(); //Click "New Film"
		
		//Fill in the fields of a new film
		driver.findElement(By.name("title")).sendKeys("Hush");
		driver.findElement(By.name("releaseYear")).sendKeys("2016");
		driver.findElement(By.name("url")).sendKeys("https://media.themoviedb.org/t/p/w300_and_h450_bestv2/wYHfD8izsrr12KNxh1Y4T1OnrTh.jpg");
		driver.findElement(By.name("synopsis")).sendKeys("Maddie, una escritora sorda, vive aislada en el bosque, hasta que un intruso enmascarado aparece en la ventana. Pero sus limites no haran de ella una presa facil.");
		
		driver.findElement(By.id("Save")).click(); //Click "Save"

		String titleNuevo = driver.findElement(By.id("film-title")).getText();
		String synopsisNuevo = driver.findElement(By.id("film-synopsis")).getText();
		assertThat(titleNuevo).isEqualTo("Hush");
		assertThat(synopsisNuevo).isEqualTo("Maddie, una escritora sorda, vive aislada en el bosque, hasta que un intruso enmascarado aparece en la ventana. Pero sus limites no haran de ella una presa facil.");
	
		driver.findElement(By.id("all-films")).click(); //Click "All Films"

		elements1 = driver.findElements(By.className("film-title"));
		elements2 = driver.findElements(By.className("year"));

		// We make sure that the movie was created
		assertTrue(findMovie(elements1,elements2,"Hush","2016"));
	}
	
	@Test
	public void removeFilmTest() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/");
		
		driver.findElement(By.id("create-film")).click(); //Click "New Film"	
		
		//Fill in the fields of a new film
		driver.findElement(By.name("title")).sendKeys("Cars");
		driver.findElement(By.name("releaseYear")).sendKeys("2006");
		driver.findElement(By.name("url")).sendKeys("https://image.tmdb.org/t/p/w1280/nqii8TqllZRainaNhQkJLNoSbMv.jpg");
		driver.findElement(By.name("synopsis")).sendKeys("El aspirante a campeon de carreras Rayo McQueen parece que esta a punto de conseguir el exito. Su actitud arrogante se desvanece cuando llega a una pequeña comunidad olvidada que le enseña las cosas importantes de la vida que habia olvidado.");

		driver.findElement(By.id("Save")).click(); //Click "Save"
		driver.findElement(By.id("remove-film")).click(); //Click "Remove"
	
		String mensaje = driver.findElement(By.id("message")).getText();
		assertThat(mensaje).isEqualTo("Film 'Cars' deleted");
		
		driver.findElement(By.id("all-films")).click(); //Click "All Films"

		List<WebElement> elements1 = driver.findElements(By.className("film-title"));
		List<WebElement> elements2 = driver.findElements(By.className("year"));

        assertFalse(findMovie(elements1,elements2,"Cars","2006"));
	}

}
