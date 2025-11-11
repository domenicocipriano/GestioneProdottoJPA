package com.domenico.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domenico.config.JwtUtil;
import com.domenico.entities.Utente;
import com.domenico.services.UtenteService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager; // Gestisce il processo di autenticazione
	private final JwtUtil jwtUtil; // Utilità per la gestione dei token JWT
	private final UtenteService utenteService; // Servizio per la gestione degli utenti
	
	// Costruttore per l'iniezione delle dipendenze
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UtenteService utenteService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.utenteService = utenteService;
	}
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody Utente utente) {
		Authentication authentication = authenticationManager.authenticate( // Effettua l'autenticazione
			new UsernamePasswordAuthenticationToken(utente.getUsername(), utente.getPassword())
		);
		System.out.println("Utente autenticato: " + authentication.getName());
		System.out.println("Dettagli autenticazione: " + authentication.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtil.generateToken(utente.getUsername());
		
		return ResponseEntity.ok(token);
	}
	@PostMapping("/register")
	public ResponseEntity<String> registraUtente(@RequestBody Utente utente) {
		utenteService.registraUtente(utente.getUsername(), utente.getPassword(), utente.getRuolo());
		System.out.println("Utente registrato: " + utente.getUsername());
		return ResponseEntity.ok("Utente registrato con successo");
	}
	@PostMapping("/register1")
	public ResponseEntity<String> registraUtenteParam(@RequestParam String username, @RequestParam String password, @RequestParam String ruolo) {
		utenteService.registraUtente(username, password, ruolo);
		System.out.println("Utente registrato: " + username);
		return ResponseEntity.ok("Utente registrato con successo");
	}
	
	
	

}
