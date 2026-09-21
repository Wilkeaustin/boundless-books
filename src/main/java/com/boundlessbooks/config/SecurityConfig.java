package com.boundlessbooks.config;

import com.boundlessbooks.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Defines password handling, login behavior, and page access rules.
 */
@Configuration
public class SecurityConfig {
	/**
	 * Creates the encoder used to safely store passwords.
	 *
	 * @return BCrypt password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Connects the user service and password encoder to Spring Security.
	 *
	 * @param service service that loads user accounts
	 * @param encoder encoder used to check passwords
	 * @return configured authentication provider
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserService service, PasswordEncoder encoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
		provider.setPasswordEncoder(encoder);
		return provider;
	}

	/**
	 * Sets which pages are public and configures login and logout.
	 *
	 * @param http security settings for web requests
	 * @param provider authentication provider for application users
	 * @return configured security filter chain
	 * @throws Exception if the security settings cannot be built
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, DaoAuthenticationProvider provider)
			throws Exception {
		return http.authenticationProvider(provider)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/books/**", "/about", "/contact", "/register", "/css/**", "/images/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/?logout").permitAll()).build();
	}
}
