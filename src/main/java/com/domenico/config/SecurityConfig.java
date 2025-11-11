package com.domenico.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // Abilita la sicurezza a livello di metodo con @PreAuthorize
public class SecurityConfig {
	
	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	public SecurityConfig(JwtUtil jwtUtil, UserDetailsService userDetailsService,
			AuthenticationConfiguration authenticationConfiguration) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.authenticationConfiguration = authenticationConfiguration;
		
	}
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
	   return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())	// Disabilita CSRF per le API REST
	        .authorizeHttpRequests(requests -> requests
	        	.requestMatchers("/api/auth/login").permitAll()  // Permetti l'accesso alla rotta di login senza autenticazione
	        	.requestMatchers("/error").permitAll()
//	        	.requestMatchers("/api/reports/**").hasRole("ADMIN") // Solo utenti con ruolo ADMIN possono accedere a /api/reports
	            .requestMatchers("/api/auth/register1").permitAll()	// Permetti l'accesso alla rotta di registrazione senza autenticazione
	            .requestMatchers("/api/auth/register").permitAll()	// Permetti l'accesso alla rotta di registrazione senza autenticazione
//	            .requestMatchers("/api/login").permitAll()
//	            .requestMatchers("/admin/**").hasRole("ADMIN")		//solo utenti con ruolo ADMIN
//	            .requestMatchers("/welcome/**").hasAuthority("read")	//solo utenti con permesso read
//	            .requestMatchers("/ciaoRest/**").hasAuthority("write")	//solo utenti con permesso write
	            .anyRequest().authenticated()	//tutte le altre richieste richiedono autenticazione
//	            .anyRequest().denyAll()		//nega tutte le richieste
//				.requestMatchers("/admin/**").hasRole("ADMIN")
//				.anyRequest().authenticated()).formLogin(Customizer.withDefaults());
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDetailsService),
	                UsernamePasswordAuthenticationFilter.class);
	    
	    // Se vuoi usare formLogin o httpBasic, puoi scommentare una di queste:
	    // .formLogin(Customizer.withDefaults());
	    // .httpBasic(Customizer.withDefaults());

	    return http.build();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {  // Utilizza BCrypt per l'hashing delle password
	    return new BCryptPasswordEncoder();
	}
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/profile").authenticated()	//solo utenti autenticati
//				.anyRequest().permitAll())		//permetti tutte le altre richieste senza autenticazione
//				.formLogin(login -> login.loginPage("/login")		//pagina di login personalizzata
//				.defaultSuccessUrl("/profile", true).permitAll())	//reindirizza a /profile dopo il login
//				.logout(logout -> logout.logoutUrl("/logout")		//URL di logout personalizzata
//				.logoutSuccessUrl("/logoutCustom")	//reindirizza dopo il logout
//				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll());	//pulisci sessione e cookie
//		
//		return http.build();	//costruisci la catena di filtri di sicurezza
//
//	}
	// Esempio con ruoli e permessi
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/api/**").permitAll()
//            .requestMatchers("/admin/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//        	.requestMatchers("/api/**").hasAnyRole("ADMIN")
//        	.requestMatchers("/admin/**").hasRole("ADMIN")
//        	.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
//        	.anyRequest().authenticated()
//        )
//        .formLogin(form -> form
//            .loginPage("/login")
//            .defaultSuccessUrl("/profile", true)
//            .permitAll()
//        )
//        .logout(logout -> logout
//            .logoutUrl("/perform-logout")			//URL di logout personalizzata
//            .logoutSuccessUrl("/logoutCustom")	//reindirizza dopo il logout
//            .invalidateHttpSession(true)			//pulisci sessione
//            .deleteCookies("JSESSIONID")			//pulisci cookie
//            .permitAll()							//consenti a tutti di accedere al logout
//        );
//    return http.build();
//}
	
	
	// Esempio con utenti in memoria (con password codificate con BCrypt)
	

	
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//		UserDetails user1 = User.withUsername("user1")
//				.password(passwordEncoder.encode("user1"))
//				.authorities("write")
//				.build();
//		UserDetails user2 = User.withUsername("user2")
//				.password(passwordEncoder.encode("user2"))
//				.authorities("read")
//				.build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
//@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return NoOpPasswordEncoder.getInstance();
//	}
	
	// Esempio con utenti in memoria (con password non codificate - non sicuro)
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("user123")
//				.roles("USER")
//				.build();
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("admin123")
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//	    return new WebMvcConfigurer() {
//	        @Override
//	        public void addCorsMappings(CorsRegistry registry) {
//	            registry.addMapping("/**")
//	                    .allowedOrigins("http://localhost:4200")
//	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//	        }
//	    };
//	}
	
	

}