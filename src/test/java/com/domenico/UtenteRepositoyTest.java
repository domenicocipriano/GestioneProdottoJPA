package com.domenico;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.domenico.entities.Utente;
import com.domenico.repositories.UtenteRepository;

@DataJpaTest
//@ContextConfiguration(classes = GestioneProdottoJpaApplication.class)
@ActiveProfiles("test")
public class UtenteRepositoyTest {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Test
	void testSaveAndFindUtente() {
		Utente utente = new Utente("testuser", "passwordhash", "USER");
		utenteRepository.save(utente);
		Optional<Utente> foundUtente = utenteRepository.findById(utente.getId());
		Assertions.assertTrue(foundUtente.isPresent(), "Utente should be found in the repository");
		Assertions.assertEquals("testuser", foundUtente.get().getUsername(), "Username should match the saved value");
		
	}
}
