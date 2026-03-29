package com.domenico;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.domenico.entities.Utente;
import com.domenico.repositories.UtenteRepository;
import com.domenico.services.UtenteService;

@ExtendWith(MockitoExtension.class)
public class UtentiServiceTest {
	
	@Mock
	private UtenteRepository utenteRepository;
	
	@InjectMocks
	private UtenteService utenteService;
	@Test
	public void testDammiTuttiUtenti() {
		List<Utente> mockUtenti = List.of(
				new Utente("domyc79", "$2a$10$d0iQQ1ZUZJ3fiJIndvG0veBFG5988nfXY9rmMwinQKvhSADhe3ni2", "user"),
				new Utente("b", "$2a$10$qoG8G54abTkc/kcRma87jurffFClaeTuozZV69kqY7Tqqz2BIbgXO", "ADMIN")
				);
		
				
		Mockito.when(utenteRepository.findAll()).thenReturn(mockUtenti);
		List<Utente> utenti = utenteService.dammiTuttiUtenti();
		Assertions.assertEquals(2, utenti.size(), "Dovrebbero esserci 2 utenti nel mock repository");
		Assertions.assertEquals("domyc79", utenti.get(0).getUsername(), "Il primo username non corrisponde");
	}
	

}
