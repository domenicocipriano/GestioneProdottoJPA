package com.domenico.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.domenico.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
	public List<Cliente>cercaPerNome(String nome);
	
	@Query("SELECT c FROM Cliente c")
	public List<Cliente>dammiTuttiClientiJPQL();
	
	@Query(value = "SELECT * FROM cliente", nativeQuery = true)
	public List<Cliente>dammiTuttiClientiNative();
	
	@Query(value = "SELECT * FROM cliente c WHERE c.nome = :nome", nativeQuery = true)
	public List<Cliente>cercaPerNomeNative(String nome);
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public List<Cliente> findAll(Sort sort);

}
