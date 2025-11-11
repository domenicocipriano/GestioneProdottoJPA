package com.domenico.dao;

import org.springframework.stereotype.Repository;

import com.domenico.entities.Prodotto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Repository
public class ProdottoDAOt {

	
	@PersistenceUnit
	private EntityManagerFactory emf;	//per gestire le operazioni di persistenza
	
	private EntityManager entityManager;	//per gestire le operazioni di persistenza
	
	public ProdottoDAOt() { 
		this.entityManager = null;
		
	}
	private EntityManager getEntityManager() {
		if (entityManager == null || entityManager.isOpen()) {
			entityManager = emf.createEntityManager();
		}
		return entityManager;
	}
	public void save(Prodotto prodotto) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(prodotto);
		em.getTransaction().commit();	
	}
	public Prodotto getById(Long id) {
		EntityManager em = getEntityManager();
		Prodotto p = em.find(Prodotto.class, id);
		em.close();
		return p;
	}
	public void updateDescrizioneAndPrezzo(Long id,String nuovaDescrizione,Double nuovoPrezzo) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Prodotto p = em.find(Prodotto.class, id);
		System.out.println("Prodotto trovato: " + p);
		p.setDescrizione(nuovaDescrizione);
		p.setPrezzo(nuovoPrezzo);
		em.getTransaction().commit();
		em.close();
	}
	public void delete(Long id) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Prodotto p = em.find(Prodotto.class, id);
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}
	

}
