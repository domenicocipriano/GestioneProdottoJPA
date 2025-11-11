package com.domenico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.domenico.dao.ProdottoDAO;
import com.domenico.entities.Prodotto;
import com.domenico.repositories.ProdottoRepository;

@Service
public class ProdottoService {
	
	private ProdottoDAO prodottoDAO;
	
	
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	public ProdottoService(ProdottoDAO prodottoDAO) {
		this.prodottoDAO = prodottoDAO;
	}
	//metodi con dao
	public void  inserisciProdotto(Prodotto prodotto) {
		prodottoDAO.save(prodotto);
	}

	public void rimuoviProdotto(Integer id) {
		prodottoDAO.delete(id);
	}
	public void rimuoviProdottoEntity(Prodotto p) {
		prodottoRepository.delete(p);
	}
	
	public void aggiornaProdotto(Prodotto p) {
		Prodotto prodotto = prodottoDAO.getById(p.getId());
		prodotto.setDescrizione(p.getDescrizione());
		prodotto.setPrezzo(p.getPrezzo());
		prodottoDAO.update(prodotto);
	}
	public void aggiornaProdotto2(Integer idPro, Integer idCat) {
		prodottoDAO.update2(idPro, idCat);
	}
	
	public Prodotto dammiProdotto(Integer id) {
		return prodottoDAO.getById(id);
	}
	
	public void aggiornaPrezzo(Integer id, Double nuovoPrezzo) {
		Prodotto prodotto = prodottoDAO.getById(id);
		prodotto.setPrezzo(nuovoPrezzo);
		prodottoDAO.update(prodotto);
	}
	
	public void inserisciNuovoProdotto() {
		prodottoDAO.newProdotto();
	}
	
	public void transazione() {
		prodottoDAO.transazione();
	}
	
	public void getAll() {
		//List<Prodotto>result = prodottoDAO.getAll();
		//result.forEach(System.out::println);
		List<Prodotto>prodotti = prodottoDAO.getAll();
		for (Prodotto p : prodotti) {
			System.out.println(p);
		}
	}
//	public void getAllNative() {
//		List<Prodotto>prodotti = prodottoDAO.getAllNative();
//		for (Prodotto p : prodotti) {
//			System.out.println(p);
//		}
//	}
	public List<Prodotto> getAllRepository() {
		return prodottoRepository.findAll();
	}
	public Prodotto getByIdNative(Long id) {
		return (Prodotto) prodottoDAO.getByIdNative(id);
	}
//	public Object getByIdNative(Long id) {
//		return prodottoDAO.getByIdNative(id);
//	}
	
	//metodi con repository
	public Prodotto cerca(Long id) {
		return prodottoRepository.existsById(id) ? prodottoRepository.findById(id).get() : null;
	}
	public Prodotto restituisciUltimoProdotto() {
		return prodottoRepository.findTopByOrderByIdDesc();
	}
	
	public Long conta() {
		return prodottoRepository.count(); //conta il numero di prodotti
	}
	
	public void restituisciProdotto(Long id) {
		Optional<Prodotto> optionalProdotto = prodottoRepository.findById(id); //ritorna un Optional
		optionalProdotto.ifPresent(System.out::println);	 //se è presente stampa il prodotto
	}
	
	public List<Prodotto> cercaPerDescrizione(String descrizione) {
		return prodottoRepository.findByDescrizione(descrizione);
	}
	
	public List<Prodotto> cercaPerPrezzoMaggioreDi(Double prezzo) {
		return prodottoRepository.findByPrezzoGreaterThan(prezzo);
	}
	
	public List<Prodotto> cercaProdottoCheContieneParolaChiave(String parolaChiave) {
		return prodottoRepository.findByDescrizioneContaining(parolaChiave);
	}
	public void updateDescrizione(Long id, String descrizione) {
		prodottoDAO.updateDescrizione(id, descrizione);
	}
	public void updatePrezzo(Long id, Double prezzo) {
		prodottoDAO.updatePrezzo(id, prezzo);
	}
	public void updateDescrizioneAndPrezzo(Long id, String descrizione, Double prezzo) {
		prodottoDAO.updateDescrizioneAndPrezzo(id, descrizione, prezzo);
	}
	public void updateProdotto(Prodotto p) {
		prodottoDAO.update(p);
	}
	public List<Prodotto> findByCategoriaId(Long id) {
		return prodottoRepository.findByCategoriaId(id);
	}
	public Page<Prodotto>findAllPaginati(int page,int size){
		Pageable pageable = PageRequest.of(page, size);
		return prodottoRepository.findAll(pageable);	
	}
	public List<Prodotto> findAllSorted(Sort sort){
		return prodottoRepository.findAll(sort);
	}
	public List<Prodotto>findAllSortedByDescrizioneAndPrezzo(){
		return prodottoRepository.findAll(Sort.by("descrizione").ascending().and(Sort.by("prezzo").descending()));
	}
	public Page<Prodotto>findAllPaginatiAndOrdinati(int page,int size, Sort sortBy, String direction){
		//Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return prodottoRepository.findAll(pageable);
		
	}
	
}
