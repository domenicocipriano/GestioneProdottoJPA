package com.domenico.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domenico.entities.Prodotto;
import com.domenico.services.ProdottoService;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {
	
	public ProdottoService prodottoService;
	
	public ProdottoController(ProdottoService prodottoService) {
		this.prodottoService = prodottoService;
	}
	//http://localhost:8081/api/prodotti?page=0&size=5
	@GetMapping
	public Page<Prodotto>getAllProdottiPaginati(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size){
		return prodottoService.findAllPaginati(page, size);
	}
	//http://localhost:8081/api/prodotti/all
	@GetMapping("/all")
	public List<Prodotto> getAllProdotti(){
		return prodottoService.getAllRepository();
	}
	//http://localhost:8081/api/prodotti/ordinato?sortBy=prezzo&direction=asc
	//http://localhost:8081/api/prodotti/ordinato?sortBy=prezzo&direction=desc
	@GetMapping("/ordinato")
	public List<Prodotto> getAllProdottiOrdinati(@RequestParam(defaultValue = "prezzo") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
	    Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    return prodottoService.findAllSorted(sort);
	}
	//http://localhost:8081/api/prodotti/ordinatoAndPaginato?page=0&size=5&sortBy=prezzo&direction=asc
	//http://localhost:8081/api/prodotti/ordinatoAndPaginato?page=0&size=5&sortBy=prezzo&direction=desc
	@GetMapping("/ordinatoAndPaginato")
	public Page<Prodotto> getAllProdottiOrdinatiAndPaginati(@RequestParam(defaultValue = "prezzo") String sortBy, @RequestParam(defaultValue = "asc") String direction,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
	    Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    return prodottoService.findAllPaginatiAndOrdinati(page, size, sort, direction);
	}
}
