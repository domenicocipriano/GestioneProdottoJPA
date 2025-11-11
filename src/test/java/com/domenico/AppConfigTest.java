package com.domenico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.domenico.services.UtenteService;

@SpringBootTest(classes = GestioneProdottoJpaApplication.class)
public class AppConfigTest {
	
	@Autowired
	private UtenteService utenteService;
	
	@Test
	public void TestBean() {
		Assertions.assertNotNull(utenteService, "UtenteService bean should be created and not null");
		
	}

}
