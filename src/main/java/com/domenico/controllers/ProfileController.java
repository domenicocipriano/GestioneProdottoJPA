package com.domenico.controllers;

//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProfileController {
	
//	@GetMapping("/profile")
//	public String userProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//	    model.addAttribute("username", userDetails.getUsername());
//	    model.addAttribute("roles", userDetails.getAuthorities());
//		return "profile";
//	}
	@GetMapping("/login")
    public String mostraLogin() {
        return "login";  // Nome del template, senza estensione .html
    }
	@GetMapping("/logout")
    public String mostraLogout() {
        return "logout";  // Nome del template, senza estensione .html
    }
	@GetMapping("/logoutCustom")
	public String mostraLoginCustom() {
		return "logoutCustom";  // Nome del template, senza estensione .html
	}
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome() {
		return "welcome";
	}
	@GetMapping("/ciaoRest")
	@ResponseBody
	public String ciaoRest() {
		return "Ciao dal controller REST!";
	}
	@GetMapping("/helloRest")
	@ResponseBody
	public String HelloRest() {
		return "Hello dal controller REST!";
	}

}
