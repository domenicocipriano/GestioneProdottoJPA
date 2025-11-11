package com.domenico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domenico.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	// metodi di query personalizzati se necessari
	Optional<Utente> findByUsername(String username);

}
