package com.domenico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domenico.entities.Cliente;
import com.domenico.services.ClienteService;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
	
	public ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public Page<Cliente> getAllClienti(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return clienteService.dammiTuttiClientiPaginati(page, size);
	}
//	@GetMapping
//	public List<Cliente>dammiTuttiClienti(){
//		return clienteService.dammiTuttiClientiFind();
//	}
	//asc ordine dalla A alla Z
	//desc ordine dalla Z alla A
	@GetMapping("/ordinato")
	public List<Cliente> getAllClientiOrdinati(@RequestParam(defaultValue = "nome") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
	    Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    return clienteService.dammiTuttiClientiOrdinati(sort);
	}
	
	

}
