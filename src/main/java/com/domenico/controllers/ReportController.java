package com.domenico.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Esempio di accesso consentito sia a USER che ad ADMIN a livello di classe
@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@GetMapping
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')") // Esempio di accesso consentito sia a USER che ad ADMIN
//	@PreAuthorize("hasRole('ADMIN')")
//	@PreAuthorize("hasRole('user')")
@PreAuthorize("hasAuthority('VIEW_REPORTS')") // Accesso consentito solo agli amministratori per visualizzare i report
	public String getReports() {
		return "Accesso consentito solo agli amministratori per visualizzare i report.";
	}

}
