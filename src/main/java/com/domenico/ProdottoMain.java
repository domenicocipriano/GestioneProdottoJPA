package com.domenico;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.domenico.entities.Categoria;
import com.domenico.entities.Cliente;
import com.domenico.entities.Prodotto;
import com.domenico.repositories.CategoriaRepository;
import com.domenico.services.CategoriaService;
import com.domenico.services.CategoriaServiceTransaction;
import com.domenico.services.ClienteService;
import com.domenico.services.ProdottoService;
import com.domenico.services.ProdottoServiceTransaction;
import com.domenico.services.UtenteService;



public class ProdottoMain {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ProdottoMain.class, args);
		
		ProdottoService prodottoService = context.getBean(ProdottoService.class);
		ClienteService clienteService = context.getBean(ClienteService.class);
		CategoriaService categoriaService = context.getBean(CategoriaService.class);
		UtenteService utenteService = context.getBean(UtenteService.class);
		CategoriaServiceTransaction categoriaServiceTransaction = context.getBean(CategoriaServiceTransaction.class);
	    ProdottoServiceTransaction prodottoServiceTransaction = context.getBean(ProdottoServiceTransaction.class);
//		Prodotto p = new Prodotto("Prodotto1", 10.0);
//		prodottoService.inserisciProdotto(p);
		System.out.println("Applicazione avviata");
//		prodottoService.rimuoviProdotto(1L);
//		Prodotto p2 = new Prodotto();
//		p2.setId(2L);
//		p2.setDescrizione("ProdottoAggiornato");
//		p2.setPrezzo(20.0);
//		prodottoService.aggiornaProdotto(p2);
//		System.out.println(prodottoService.dammiProdotto(2L));
//		prodottoService.aggiornaPrezzo(2L, 55.0);
//		prodottoService.inserisciNuovoProdotto();
//		prodottoService.transazione();
//		Prodotto p3 = new Prodotto("ProdottoTransazione", 30.0);
//		prodottoServiceTransaction.inserisciProdottoTransazione(p3);
//		prodottoService.getAll();
		//prodottoService.getAllNative();
		//categoriaService.dammitutteCategorie();
		// Esempio di utilizzo del metodo cerca
//		System.out.println(prodottoService.cerca(2L));
		// Esempio di utilizzo del metodo conta
//		System.out.println(prodottoService.conta());
		// Esempio di utilizzo del metodo restituisciProdotto
		//prodottoService.restituisciProdotto(2L);
		// Esempio di ricerca per descrizione
//		List<Prodotto> prodottiTrovatiPerDescrizione = prodottoService.cercaPerDescrizione("ProdottoAggiornato");
//		if (prodottiTrovatiPerDescrizione.isEmpty()) {
//		    System.out.println("Nessun prodotto trovato con la descrizione specificata.");
//		} else {
//		    prodottiTrovatiPerDescrizione.forEach(System.out::println);
//		}
		// Esempio di ricerca per prezzo maggiore di
//		List<Prodotto> prodottiTrovatiPerPrezzoMaggiore = prodottoService.cercaPerPrezzoMaggioreDi(15.0);
//		if (prodottiTrovatiPerPrezzoMaggiore.isEmpty()) {
//		    System.out.println("Nessun prodotto trovato con il prezzo maggiore di quello specificato.");
//		} else {
//		    prodottiTrovatiPerPrezzoMaggiore.forEach(System.out::println);
//		}
		// Esempio di ricerca contienente parola chiave
//		List<Prodotto> prodottiTrovatiPerParolaChiave = prodottoService.cercaProdottoCheContieneParolaChiave("Agg");
//		if (prodottiTrovatiPerParolaChiave.isEmpty()) {
//		    System.out.println("Nessun prodotto trovato contenente la parola chiave specificata.");
//		} else {
//		    prodottiTrovatiPerParolaChiave.forEach(System.out::println);
//		}
//		prodottoService.updateDescrizione(2L, "DescrizioneAggiornata");
		//prodottoService.updateDescrizioneAndPrezzo(null, null, null);
//		prodottoServiceTransaction.aggiornaDescrizioneAndPrezzoTransazione(4L, "DescrizioneTransazione", 99.99);
//		Object p4 = prodottoService.getByIdNative(4L);
//		System.out.println("prodotto: "+p4);
//		Categoria c = new Categoria("TV");
//		categoriaServiceTransaction.inserisciCategoriaTransazione(c);
//		Prodotto p5 = new Prodotto("TV Samsung", 499.99);
//		prodottoServiceTransaction.inserisciProdottoTransazione(p5);
//		Prodotto p6 = new Prodotto("TV LG", 599.99);
//		prodottoServiceTransaction.inserisciProdottoTransazione(p6);
//		prodottoService.aggiornaProdotto2(5,1);
//		Categoria c7 = categoriaServiceTransaction.dammiCategoriaTransazione(1);
//		System.out.println("Categoria trovata: " + c7);
//		p5.setCategoria(c7);
//		prodottoServiceTransaction.inserisciProdottoTransazione(p5);	
//		Prodotto ultimoProdotto = prodottoService.restituisciUltimoProdotto();
//		System.out.println("Ultimo prodotto inserito: " + ultimoProdotto);
//		prodottoService.aggiornaProdotto2(ultimoProdotto.getId(),1);
		//System.out.println(prodottoService.getByIdNative(4L));
		//System.out.println(prodottoService.findByCategoriaId(1L));
//		Prodotto prodottoTrovato = prodottoService.cerca(4L);
//		if(prodottoTrovato != null) {
//			System.out.println("Prodotto trovato: " + prodottoTrovato);
//			prodottoService.rimuoviProdottoEntity(prodottoTrovato);
//			if(prodottoService.cerca(4L) == null) {
//				System.out.println("Prodotto rimosso con successo");
//			} else {
//				System.out.println("Errore nella rimozione del prodotto");
//			}
//		}else {
//			System.out.println("Prodotto non trovato");
//		}
//		Cliente cliente = new Cliente("Mario", "Rossi");
//		Cliente cliente2 = new Cliente("Luigi", "Verdi");
//		clienteService.inserisciCliente(cliente2);
		List<Cliente> clienti = clienteService.dammiTuttiClienti();
		for (Cliente cl : clienti) {
			System.out.println("cliente: "+cl);
		}
//		Cliente clienteTrovato = clienteService.dammiClientePerId(4);
//		if(clienteTrovato != null) {
//			System.out.println("Cliente trovato: " + clienteTrovato);
//		}else {
//			System.out.println("Cliente non trovato");
//		}
//		clienteService.delete(4);
//		List<Cliente>clientiPerNome = clienteService.cercaPerNome("Mario");
//		System.out.println(clientiPerNome);
//		System.out.println(clienteService.cercaPerNomeNative("Luigi"));
		
//		UserDetails u = utenteService.loadUserByUsername("domyc79");
//		boolean matches = passwordEncoder().matches("password123", u.getPassword());
//		System.out.println(u);
		String username = "domyc79";
		String password = "password123";
		utenteService.loginUtente(username, password);
		String pippo = "PIPPO";
		String hash = BCrypt.hashpw(pippo, BCrypt.gensalt());
		System.out.println("Hash di PIPPO: " + hash);
		if(BCrypt.checkpw(pippo, hash)) {
			System.out.println("La password corrisponde al valore hashato.");
		} else {
			System.out.println("La password NON corrisponde al valore hashato.");
		}
	}

}
