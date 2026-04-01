package com.apps.authservice.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenBlacklist {

	private final Set<String> blacklist = new HashSet<>();

	public void blacklistToken(String token) {
		blacklist.add(token);
	}

	public boolean isBlacklisted(String token) {
		return blacklist.contains(token);
	}
}