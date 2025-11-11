package com.domenico.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.domenico.entities.Categoria;
import com.domenico.entities.Prodotto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class ProdottoDAO {
	
	@PersistenceContext
	private EntityManager em;  //EntityManager per gestire le operazioni di persistenza
	
	
	//persist
	//merge
	//remove
	//find
	//remove
	//createQuery
	
	@Transactional
	public void save(Prodotto prodotto) { //per le operazioni di scrittura serve la transazione
		em.persist(prodotto);   //persist per salvare un'entità
	}
	
	public Prodotto getById(Integer integer) {   //per le operazioni di lettura non serve la transazione
		return em.find(Prodotto.class, integer);   //find per cercare un'entità
	}
	@Transactional
	public void delete(Integer id) {   //per le operazioni di scrittura serve la transazione
		Prodotto p = getById(id);   //prima di eliminare un'entità bisogna prima trovarla
		em.remove(p);  //remove per eliminare un'entità
	}
	public void deleteEntity(Prodotto p) {
		em.remove(p);
	}
	@Transactional
	public void update(Prodotto prodotto) {   //per le operazioni di scrittura serve la transazione
		em.merge(prodotto);   //merge per aggiornare un'entità
	}
	@Transactional
	public void update2(Integer idPro,Integer idCat) {   //per le operazioni di scrittura serve la transazione
		Prodotto p = em.find(Prodotto.class, idPro);
		System.out.println("Prodotto trovato: " + p);
		Categoria c = em.getReference(Categoria.class, idCat);
		if(p != null && c != null) {
			p.setCategoria(c);
			em.merge(p);
		}
		
	}
	@Transactional
	public void newProdotto() {
		Prodotto p = new Prodotto("Nuovo prodotto", 99.99);
		em.persist(p);
		p.setPrezzo(79.99);
	}
    //EntityManagerFactory --> EntityManager --> EntityTransaction
	public void transazione() {
		EntityManagerFactory emf = em.getEntityManagerFactory(); //ottenere l'EntityManagerFactory dall'EntityManager
		EntityManager e = emf.createEntityManager(); //creare un nuovo EntityManager
		EntityTransaction tx = e.getTransaction();//ottenere la transazione dall'EntityManager
		tx.begin();
		// operazioni di persistenza
		Prodotto p = new Prodotto("Prodotto Transazione", 49.99);
		e.persist(p);
		tx.commit();
		e.close();
		
	}
	//metodi di restituzione
	public List<Prodotto>getAll(){ //JPQL
		Query query = em.createQuery("SELECT p FROM Prodotto p LEFT JOIN FETCH p.categoria"); //JPQL
		return query.getResultList();
	}
//	public List<Prodotto>getAllNative(){ //SQL nativo
//		Query query = em.createNativeQuery("SELECT * FROM prodotto",Prodotto.class); //SQL nativo
//		return query.getResultList();
//	}
//	public Object getByIdNative(Long id){ //SQL nativo
//		Query query = em.createNativeQuery("SELECT * FROM prodotto WHERE id = :id",Prodotto.class); //SQL nativo
//		query.setParameter("id", id);
//		return query.getSingleResult();
//	}
	public Prodotto getByIdNative(Long id){ //SQL nativo
		Query query = em.createNativeQuery("SELECT * FROM prodotto WHERE id = :id",Prodotto.class); //SQL nativo
		query.setParameter("id", id);
		return (Prodotto) query.getSingleResult();
	}
	
	//metodi di update
	@Transactional
	public void updateDescrizione(Long id,String descrizione) {
//		Query query = em.createQuery("UPDATE Prodotto p SET p.descrizione = :descrizione WHERE p.id = :id");
//		query.setParameter("descrizione", descrizione);
//		query.setParameter("id", id);
//		query.executeUpdate();
		
		Prodotto p = em.find(Prodotto.class, id);
		if(p != null) {
			p.setDescrizione(descrizione);
		}
	}
	@Transactional
	public void updatePrezzo(Long id,Double prezzo) {
		Prodotto p = em.find(Prodotto.class, id);
		if(p != null) {
			p.setPrezzo(prezzo);
		}
	}
	@Transactional
	public void updateDescrizioneAndPrezzo(Long id, String descrizione, Double prezzo) {
		if(descrizione == null || prezzo == null) {
			throw new IllegalArgumentException("Descrizione e prezzo non possono essere null");
		}
		Prodotto p = em.find(Prodotto.class, id);
		if(p != null) {
			p.setDescrizione(descrizione);
			p.setPrezzo(prezzo);
		}
	}
	
	
	
	
	

}
