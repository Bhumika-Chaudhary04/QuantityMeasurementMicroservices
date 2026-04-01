package com.apps.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO {

	@NotBlank(message = "Email cannot be empty")
	private String email;

	@NotBlank(message = "Name cannot be empty")
	private String name;

	@NotBlank(message = "Password cannot be empty")
	private String password;
}