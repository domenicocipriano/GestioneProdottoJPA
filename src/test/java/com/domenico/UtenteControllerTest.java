package com.domenico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.domenico.config.JwtAuthenticationFilter;
import com.domenico.config.JwtUtil;
import com.domenico.config.SecurityConfig;
import com.domenico.controllers.UtenteController;
import com.domenico.entities.Utente;
import com.domenico.services.UtenteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UtenteController.class,
excludeFilters = @ComponentScan.Filter(
    type = FilterType.ASSIGNABLE_TYPE,
    classes = {SecurityConfig.class, JwtAuthenticationFilter.class, JwtUtil.class}
)
)
public class UtenteControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UtenteService utenteService;
	

	
	
	
	@Test
	void testGetAllUtenti() throws Exception {
		// Implementa il test per il metodo getAllUtenti
		List<Utente> utenti = List.of(new Utente("user1", "pass1", "ROLE_USER"),
				                     new Utente("user2", "pass2", "ROLE_ADMIN"));
		
		Mockito.when(utenteService.dammiTuttiUtenti()).thenReturn(utenti);
		mockMvc.perform(get("/api/utenti"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.length()").value(2))
		       .andExpect(jsonPath("$[0].username").value("user1"))
		       .andExpect(jsonPath("$[1].username").value("user2"));
	
		
	}
	@Test
	void testCreateUtente() throws Exception {
		// Implementa il test per il metodo createUtente
		Utente nuovoUtente = new Utente("newuser", "newpass", "ROLE_USER");
		
		mockMvc.perform(post("/register")
		       .contentType(MediaType.APPLICATION_JSON)
		       .content(new ObjectMapper().writeValueAsString(nuovoUtente)))
			   .andExpect(status().isOk())
			   		       .andExpect(jsonPath("username").value("newuser"))
		       .andExpect(jsonPath("$.ruolo").value("ROLE_USER"));
		       
		
	}
	
	

}
