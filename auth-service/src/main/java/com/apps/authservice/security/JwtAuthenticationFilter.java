package com.apps.authservice.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apps.authservice.entity.User;
import com.apps.authservice.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;
	private final TokenBlacklist tokenBlacklist;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepo, TokenBlacklist tokenBlacklist) {
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
		this.tokenBlacklist = tokenBlacklist;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);

		if (tokenBlacklist.isBlacklisted(token)) {
			chain.doFilter(request, response);
			return;
		}

		if (!jwtUtil.validateToken(token)) {
			chain.doFilter(request, response);
			return;
		}

		String email = jwtUtil.extractEmail(token);

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			Optional<User> user = userRepo.findByEmail(email);

			if (user.isPresent()) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null,
						List.of(new SimpleGrantedAuthority("ROLE_USER")));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		chain.doFilter(request, response);
	}
}