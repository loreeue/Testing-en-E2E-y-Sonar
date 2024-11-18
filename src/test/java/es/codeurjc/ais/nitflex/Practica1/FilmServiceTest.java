package es.codeurjc.ais.nitflex.Practica1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.codeurjc.ais.nitflex.film.*;
import es.codeurjc.ais.nitflex.utils.*;
import es.codeurjc.ais.nitflex.notification.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class FilmServiceTest {
	
	private FilmRepository repositorioMock;
	private NotificationService notificadorMock;
	private UrlUtils urlMock;
	private FilmService fservicio;
	private Film peli;
	
	@BeforeEach
	public void setUp() {
		repositorioMock = mock(FilmRepository.class);
		notificadorMock = mock(NotificationService.class);
		urlMock = mock(UrlUtils.class);
		peli = mock(Film.class);	

		fservicio = new FilmService(repositorioMock, notificadorMock, urlMock);	
	}

	@Test
	public void saveFilmCorrectURLTest() {
		//Given
		when(peli.getTitle()).thenReturn("Hush");
		when(peli.getUrl()).thenReturn("https://media.themoviedb.org/t/p/w300_and_h450_bestv2/wYHfD8izsrr12KNxh1Y4T1OnrTh.jpg");
		
		when(repositorioMock.save(peli)).thenReturn(peli);
		
		//When
		assertDoesNotThrow(() -> {
	        fservicio.save(peli);
	    }, "No se debe lanzar ninguna excepción");

		//Then
		verify(repositorioMock).save(peli);
		verify(notificadorMock).notify("Film Event: Film with title=Hush was created");
	}
	
	@Test
    public void saveFilmIncorrectURLTest() {
		//Given
        String urlIncorrecta = "http://urlincorrecta.com";
        when(peli.getTitle()).thenReturn("Hush");
        when(peli.getUrl()).thenReturn(urlIncorrecta);

		when(repositorioMock.save(peli)).thenReturn(peli);
   
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The url is not an image resource")).when(urlMock).checkValidImageURL(anyString());
        
        //When
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->{			
			fservicio.save(peli);
		});
 
        //Then
        assertEquals(HttpStatus.BAD_REQUEST+ " \"The url is not an image resource\"", ex.getMessage());
        verify(repositorioMock, never()).save(peli);
        verify(notificadorMock, never()).notify(anyString());     
	}
	
}
