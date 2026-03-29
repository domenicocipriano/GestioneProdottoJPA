package com.domenico.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UTENTE")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String username;
	
	private String password;
	private String ruolo;
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Utente(String username, String password, String ruolo) {
		super();
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}
	public List<String> getAuthorities() {
		List<String> authorities = new ArrayList<>();
		//authorities.add("ROLE_" + this.ruolo.toUpperCase());
		if("ADMIN".equalsIgnoreCase(getRuolo())) {
			authorities.add("VIEW_REPORTS");
		}else if("USER".equalsIgnoreCase(getRuolo())) {
			authorities.add("VIEW_PRODUCTS");
		}
		return authorities;
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", ruolo=" + ruolo + "]";
	}
	
	

}
