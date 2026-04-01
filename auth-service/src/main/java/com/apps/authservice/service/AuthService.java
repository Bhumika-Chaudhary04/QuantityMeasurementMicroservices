package com.apps.authservice.service;

import com.apps.authservice.dto.AuthResponseDTO;
import com.apps.authservice.dto.LoginRequestDTO;
import com.apps.authservice.dto.RegisterRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

	AuthResponseDTO register(RegisterRequestDTO dto);

	AuthResponseDTO login(LoginRequestDTO dto);

	String logout(HttpServletRequest request);
}