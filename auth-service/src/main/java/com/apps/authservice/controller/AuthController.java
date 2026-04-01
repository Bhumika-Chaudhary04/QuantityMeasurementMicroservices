package com.apps.authservice.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apps.authservice.dto.AuthResponseDTO;
import com.apps.authservice.dto.LoginRequestDTO;
import com.apps.authservice.dto.RegisterRequestDTO;
import com.apps.authservice.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	@GetMapping("/")
	public void redirectToGoogle(HttpServletResponse response) throws IOException {
		response.sendRedirect("/oauth2/authorization/google");
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
		return ResponseEntity.ok(service.register(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
		return ResponseEntity.ok(service.login(dto));
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		return ResponseEntity.ok(service.logout(request));
	}
}