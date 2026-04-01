package com.apps.qmaservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuantityDTO {

	@NotNull(message = "Value cannot be null")
	private Double value;

	@NotBlank(message = "Unit cannot be empty")
	private String unit;

	@NotBlank(message = "Measurement type required")
	private String measurementType;
	
}