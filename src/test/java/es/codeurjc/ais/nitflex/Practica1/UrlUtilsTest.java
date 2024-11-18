package es.codeurjc.ais.nitflex.Practica1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import es.codeurjc.ais.nitflex.utils.UrlUtils;

class UrlUtilsTest {
	
	private UrlUtils urlUtils;
	
	@BeforeEach
	public void setUp() {
		urlUtils = new UrlUtils();
	}
	
	@Test
	public void URLFormatIncorrectTest() { 
		//Given
		String url = "esto-no-es-una-url";
		
		//When
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->{			
			urlUtils.checkValidImageURL(url);
		});
		
		//Then
		assertEquals(HttpStatus.BAD_REQUEST+" \"The url format is not valid\"", ex.getMessage());
	}

	@Test
	public void URLNotExistTest() {
		//Given
        String url = "https://www.themoviedb.org/image.png";
		
		//When
		ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->{			
			urlUtils.checkValidImageURL(url);
		});
		
		//Then
		assertEquals(HttpStatus.BAD_REQUEST+ " \"Url resource does not exists\"", ex.getMessage());
	}

}
