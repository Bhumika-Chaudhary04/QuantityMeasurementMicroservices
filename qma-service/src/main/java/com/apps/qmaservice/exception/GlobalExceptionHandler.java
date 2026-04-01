package com.apps.qmaservice.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {
		return ResponseEntity.badRequest().body(Map.of("error", true, "message", ex.getMessage()));
	}
}