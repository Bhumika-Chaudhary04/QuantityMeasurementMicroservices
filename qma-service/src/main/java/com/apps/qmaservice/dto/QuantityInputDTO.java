package com.apps.qmaservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuantityInputDTO {

	@NotNull
	@Valid
	private QuantityDTO thisQuantityDTO;

	@NotNull
	@Valid
	private QuantityDTO thatQuantityDTO;

	private String outputUnit;
}