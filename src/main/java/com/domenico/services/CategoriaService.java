package com.domenico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.domenico.dao.CategoriaDAO;
import com.domenico.entities.Categoria;
import com.domenico.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaDAO categoriaDAO;
	
	private CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaDAO categoriaDAO, CategoriaRepository categoriaRepository) {
		this.categoriaDAO = categoriaDAO;
		this.categoriaRepository = categoriaRepository;
		
	}
	
	public void inserisciCategoriaTransazione(Categoria c) {
		categoriaDAO.inserisciCategoria(c);
	}
	public Categoria dammiCategoriaRepository(Integer id) {
		Optional<Categoria> c = categoriaRepository.findById(id);
		return c.orElse(null);
	}
	public List<Categoria>dammitutteCategorie(){
		return categoriaDAO.dammiTutteCategorie();
	}
	
	
	


}
