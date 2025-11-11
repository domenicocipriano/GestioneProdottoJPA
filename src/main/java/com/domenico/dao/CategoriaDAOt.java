package com.domenico.dao;

import org.springframework.stereotype.Repository;

import com.domenico.entities.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceUnit;

@Repository
public class CategoriaDAOt {
	
	@PersistenceUnit
	private EntityManagerFactory emf;	//per gestire le operazioni di persistenza
	private EntityManager em;
	
	public CategoriaDAOt() {
		em = null;
	}
	public EntityManager getEntityManager() {
		em = emf.createEntityManager();
		return em;
	}
	public void inserisciCategoria(Categoria c){
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(c);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	public Categoria dammiCategoria(Integer id) {
		EntityManager entityManager = getEntityManager();
		Categoria c = entityManager.find(Categoria.class, id);
		entityManager.close();
		return c;
	}
	public Categoria dammiCategoriaDue(Integer id) {
		EntityManager entityManager = getEntityManager();
		Categoria c = entityManager.getReference(Categoria.class, id);
		return c;
	}
	public Categoria dammiCategoriatre(Integer id) {
	    EntityManager entityManager = getEntityManager();
	    try {
	        // Query con LEFT JOIN FETCH sulle relazioni, aggiungendo DISTINCT per evitare duplicati
	        Categoria categoria = entityManager.createQuery(
	            "SELECT DISTINCT c FROM Categoria c LEFT JOIN FETCH c.prodotto WHERE c.id = :id",
	            Categoria.class
	        )
	        .setParameter("id", id)
	        .getSingleResult();

	        return categoria;

	    } catch (NoResultException e) {
	        return null; // se la categoria non esiste
	    } finally {
	        entityManager.close();
	    }
	}

}
