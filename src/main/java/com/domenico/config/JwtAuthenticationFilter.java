package com.domenico.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	// Implementa il metodo doFilterInternal per gestire l'autenticazione JWT
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		System.out.println("Request Path: " + path);
		if(path.startsWith("/h2-console/")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = request.getHeader("Authorization"); // Estrae il token dall'header Authorization
		if(token != null && token.startsWith("Bearer ")) { // Controlla che il token sia presente
			token = token.substring(7); // Rimuove il prefisso "Bearer "
			String username = jwtUtil.extractUsername(token); // Estrae lo username dal token
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Controlla che l'utente non sia già autenticato
				UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Carica i dettagli dell'utente
				if(jwtUtil.validateToken(token, userDetails.getUsername())) {  // Valida il token
					// Imposta l'autenticazione nel contesto di sicurezza
					UsernamePasswordAuthenticationToken authentication = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Crea un token di autenticazione
					SecurityContextHolder.getContext().setAuthentication(authentication); // Imposta l'autenticazione nel contesto di sicurezza
				}
			}
		}
		filterChain.doFilter(request, response);
		
		
	}


}
