package com.domenico.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	//private static final long EXPIRATION_TIME = 86400000; // 1 giorno in millisecondi
	private static final long EXPIRATION_TIME = 3600000; // 1 ora in millisecondi
	
	// Genera un token JWT per un dato username
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(secretKey)
				.compact();
	}
	
	// Valida un token e restituisce true se valido
    public boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
 // Estrae username dal token
	public String extractUsername(String token) {
	    return getClaims(token).getSubject();
	 }
	// Controlla se il token è scaduto
	private boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}
	// Estrae i claims dal token
	private Claims getClaims(String token) {
	    return Jwts.parserBuilder()
	        .setSigningKey(secretKey)
	        .build()
	        .parseClaimsJws(token)
	        .getBody();
	}
	 
	
	
	
	
	
	
//	public String extractUsername(String token) {
//		return Jwts.parser()
//				.setSigningKey(secretKey)
//				.parseClaimsJws(token)
//				.getBody()
//				.getSubject();
//	}
	  // Estrae username dal token
	
//	public boolean validateToken(String token, String username) {
//		return username.equals(extractUsername(token)) && !isTokenExpired(token);
//		
//	}

//	private boolean isTokenExpired(String token) {
//		 return Jwts.parser()
//				.setSigningKey(secretKey)
//				.parseClaimsJws(token)
//				.getBody()
//				.getExpiration()
//		 		.before(new Date());
//	
//	}

	

}
