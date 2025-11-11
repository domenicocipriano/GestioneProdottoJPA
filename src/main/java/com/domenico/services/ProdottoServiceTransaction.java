package com.domenico.services;

import org.springframework.stereotype.Service;

import com.domenico.dao.ProdottoDAOt;
import com.domenico.entities.Prodotto;

@Service
public class ProdottoServiceTransaction {
	
	private ProdottoDAOt prodottoDAOt;
	
	public ProdottoServiceTransaction(ProdottoDAOt prodottoDAOt) {
		this.prodottoDAOt = prodottoDAOt;
	}

	public void inserisciProdottoTransazione(Prodotto prodotto) {
		prodottoDAOt.save(prodotto);
	}
	public Prodotto dammiProdottoTransazione(Long id) {
		return prodottoDAOt.getById(id);
	}
	public void aggiornaDescrizioneAndPrezzoTransazione(Long id, String nuovaDescrizione, Double nuovoPrezzo) {
		prodottoDAOt.updateDescrizioneAndPrezzo(id, nuovaDescrizione, nuovoPrezzo);
	}
	public void rimuoviProdottoTransazione(Long id) {
		prodottoDAOt.delete(id);
	}

}
