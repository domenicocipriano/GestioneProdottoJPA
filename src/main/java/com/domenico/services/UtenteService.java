package com.domenico.services;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.domenico.entities.Utente;
import com.domenico.repositories.UtenteRepository;

@Service
public class UtenteService{
	
	private final UtenteRepository utenteRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
		this.utenteRepository = utenteRepository;
		this.passwordEncoder = passwordEncoder;
	}
	// Metodo per caricare i dettagli dell'utente in base al nome utente
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = utenteRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con username: " + username));
		
		
		return User.withUsername(utente.getUsername())
			.password(utente.getPassword())
			.roles(utente.getRuolo())
			.build();
	
	}
	// Metodo per la registrazione di un nuovo utente
	public void registraUtente(String username, String rawPassword, String ruolo) {
		String hashedPassword = passwordEncoder.encode(rawPassword);	// Hashing della password
		Utente nuovoUtente = new Utente(username, hashedPassword,ruolo.toUpperCase());	// Creazione del nuovo utente con password codificata
		utenteRepository.save(nuovoUtente);	// Salvataggio del nuovo utente nel repository
	}
	// Metodo per il login dell'utente
	public boolean loginUtente(String username, String password) {
		UserDetails utente = loadUserByUsername(username);	// Caricamento dei dettagli dell'utente
		boolean match = passwordEncoder.matches(password, utente.getPassword());	// Verifica della corrispondenza della password
		if (match) {
			return true;	// Login riuscito
		} else {
			return false;	// Login fallito
			
		}
		
	}
	public List<Utente> dammiTuttiUtenti(){
		return utenteRepository.findAll();
	}
	

}
