package com.domenico.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.domenico.entities.Cliente;
import com.domenico.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	public void inserisciCliente(Cliente cliente) {
		int countprec = clienteRepository.findAll().size();
		System.out.println("Numero clienti prima dell'inserimento: " + countprec);
		clienteRepository.save(cliente);	
		if(countprec < clienteRepository.findAll().size()) {
			System.out.println("Cliente inserito correttamente");
			System.out.println("Numero clienti dopo l'inserimento: " + clienteRepository.findAll().size());
		} else {
			System.out.println("Errore nell'inserimento del cliente");
		}
	}
//	public List<Cliente> dammiTuttiClienti(){
//		return clienteRepository.findAll();
//	}
	public List<Cliente> dammiTuttiClienti() {
		return clienteRepository.dammiTuttiClientiJPQL();
	}
	public List<Cliente>dammiTuttiClientiFind(){
		return clienteRepository.findAll();
	}
	public List<Cliente> cercaPerNomeNative(String nome) {
		return clienteRepository.cercaPerNomeNative(nome);
	}
	public Cliente dammiClientePerId(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	public void delete(Integer id) {
		int countprec = clienteRepository.findAll().size();
		Cliente c = dammiClientePerId(id);
		if(c != null) {
			clienteRepository.delete(c);
			if(countprec > clienteRepository.findAll().size()) {
				System.out.println("Cliente eliminato correttamente");
			} else {
				System.out.println("Errore nell'eliminazione del cliente");
			}
		} else {
			System.out.println("Cliente non trovato");
		}
	}
	public List<Cliente> cercaPerNome(String nome){
		return clienteRepository.cercaPerNome(nome);
	}
	public Page <Cliente>dammiTuttiClientiPaginati(int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		return clienteRepository.findAll(pageable);
	}
//	public List<Cliente> dammiTuttiClientiOrdinati(Sort sort){
//		return clienteRepository.findAll(Sort.by("cognome").descending());
//	}
	public List<Cliente> dammiTuttiClientiOrdinati(Sort sort){
		return clienteRepository.findAll(sort);
	}
	

}
