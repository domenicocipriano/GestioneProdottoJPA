package com.domenico.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.domenico.entities.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CategoriaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void inserisciCategoria(Categoria c) {
		em.persist(c);
		
	}
	public Categoria dammiCategoria(Integer id) {
		return em.find(Categoria.class, id);
	}
	public List<Categoria>dammiTutteCategorie(){
		String sql = "SELECT c FROM Categoria c";
		return em.createQuery(sql).getResultList();
	}
	
	

}
