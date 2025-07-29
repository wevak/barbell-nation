package com.barbellnation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // java config class - equivalent to bean config xml file
@EnableWebSecurity // enables spring security
@EnableMethodSecurity // enables req handling
public class SecurityConfiguration {
	private final PasswordEncoder passwordEncoder;
	private final CustomJwtFilter jwtFilter;

	public SecurityConfiguration(PasswordEncoder passwordEncoder, CustomJwtFilter jwtFilter) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.jwtFilter = jwtFilter;
	}

	// configure bean to customize spring security filter chain
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()) // disable CSRF token generation
				.authorizeHttpRequests(request -> request
						.requestMatchers("/v*/api-docs/**", "/swagger-ui/**", "/users/signup",
								"/users/signin")
						.permitAll()
						// after react integration - to allow pre flight requests -
						// permit all - HTTP methods - OPTIONS
						.requestMatchers(HttpMethod.OPTIONS).permitAll()

						.requestMatchers("/owners", "/trainers", "/packages/add").hasRole("ADMIN")

						.requestMatchers("/products/purchase/**").hasRole("CUSTOMER")

						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// configure Auth Manager bean using a dependency
	// -AuthenticationConfiguration
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
