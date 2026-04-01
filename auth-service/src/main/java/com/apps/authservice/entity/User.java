package com.apps.authservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AuthProvider provider;
}