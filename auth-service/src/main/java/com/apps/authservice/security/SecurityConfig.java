package com.apps.authservice.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apps.authservice.repository.UserRepository;

@Configuration
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;
	private final TokenBlacklist tokenBlacklist;

	public SecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, TokenBlacklist tokenBlacklist) {
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
		this.tokenBlacklist = tokenBlacklist;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions(frame -> frame.disable()))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/",
							"/auth/**",
							"/swagger-ui/**",
							"/v3/api-docs/**",
							"/h2-console/**")
					.permitAll()
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.addFilterBefore(
					new JwtAuthenticationFilter(jwtUtil, userRepo, tokenBlacklist),
					UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(List.of(
				"http://localhost:5500",
				"http://127.0.0.1:5500",
				"http://localhost:5173",
				"http://127.0.0.1:5173",
				"http://localhost:4200",
				"http://127.0.0.1:4200"
		));

		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("Authorization"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}