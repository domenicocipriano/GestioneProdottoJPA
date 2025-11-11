package com.domenico.services;

import org.springframework.stereotype.Service;

import com.domenico.dao.CategoriaDAOt;
import com.domenico.entities.Categoria;

@Service
public class CategoriaServiceTransaction {
	
	private CategoriaDAOt categoriaDAOt;
	
	public CategoriaServiceTransaction(CategoriaDAOt categoriaDAOt) {
		this.categoriaDAOt = categoriaDAOt;
	}
	public void inserisciCategoriaTransazione(Categoria c) {
		categoriaDAOt.inserisciCategoria(c);
	}
	public Categoria dammiCategoriaTransazione(Integer id) {
		return categoriaDAOt.dammiCategoriatre(id);
	}
	public Categoria dammiCategoriaDueTransazione(Integer id) {
		return categoriaDAOt.dammiCategoriaDue(id);
	}

}
