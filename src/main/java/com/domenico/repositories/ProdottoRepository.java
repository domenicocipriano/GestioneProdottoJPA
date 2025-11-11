package com.domenico.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.domenico.entities.Prodotto;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
	List<Prodotto>findByDescrizione(String descrizione);
	List<Prodotto>findByPrezzoGreaterThan(Double prezzo);
	List<Prodotto>findByPrezzoLessThan(Double prezzo);
	List<Prodotto>findByPrezzoBetween(Double prezzoMin, Double prezzoMax);
	
	List<Prodotto> findByDescrizioneContaining(String parolaChiave);
	List<Prodotto> findByDescrizioneStartsWith(String inizioStringa);
	List<Prodotto> findByDescrizioneEndsWith(String fineStringa);
	
	List<Prodotto> findByDescrizioneAndPrezzo(String descrizione, Double prezzo);
	List<Prodotto>findByPrezzoLessThanEqualAndDescrizioneContaining(Double prezzo, String parolaChiave);
	List<Prodotto>findByPrezzoLessThanEqualAndDescrizione(Double prezzo, String descrizione);
	
	@Query("SELECT p FROM Prodotto p WHERE p.descrizione = :descrizione")
	List<Prodotto>findProdottoByDescrizione(@Param("descrizione") String descrizione);
	Prodotto findTopByOrderByIdDesc();
	//List<Prodotto>findByCategoriaId(Long id);
	@Query("SELECT DISTINCT p FROM Prodotto p WHERE p.categoria.id = :id")
	List<Prodotto> findByCategoriaId(@Param("id") Long id);
	
	Page<Prodotto>findAll(Pageable pageable); // Paginazione
	List<Prodotto>findAll(Sort sort);
}
