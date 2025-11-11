package com.domenico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domenico.entities.Utente;
import com.domenico.services.UtenteService;

@RestController
public class UtenteController {
	
	private final UtenteService utenteService;
	
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	@PostMapping("/register")
	public ResponseEntity<String> registraUtente(@RequestBody Utente utente) {
		
		utenteService.registraUtente(utente.getUsername(), utente.getPassword(), utente.getRuolo());
		System.out.println("Utente registrato: " + utente.getUsername());
		return ResponseEntity.ok("Utente registrato con successo");
	}
	@PostMapping("/api/login")
	public ResponseEntity<String> loginUtente(@RequestBody Utente utente) {
		if(utenteService.loginUtente(utente.getUsername(), utente.getPassword())) {
			return ResponseEntity.ok("Login avvenuto con successo per l'utente: " + utente.getUsername());
		}else {
			return ResponseEntity.status(401).body("Credenziali non valide per l'utente" + utente.getUsername());
		}
		
	}
	@GetMapping("/api/utenti")
	public ResponseEntity<List<Utente>> getAllUtenti(){
		List<Utente>utenti = utenteService.dammiTuttiUtenti();
		return ResponseEntity.ok(utenti);
	}
	

}
