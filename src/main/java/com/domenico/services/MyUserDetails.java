package com.domenico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.domenico.entities.Utente;
import com.domenico.repositories.UtenteRepository;

@Service
public class MyUserDetails implements UserDetailsService {
	
	private final UtenteRepository utenteRepository;
	
	public MyUserDetails(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String usr, pwd;
		List<GrantedAuthority> authorities;
		Optional<Utente>utenti = utenteRepository.findByUsername(username);
		if(!utenti.isPresent()) {
			throw new UsernameNotFoundException("Utente non trovato con username: " + username);
		} else {
			usr = utenti.get().getUsername();
			pwd = utenti.get().getPassword();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + utenti.get().getRuolo()));
			
			
		}
		return new User(usr, pwd, authorities);
	}

}
