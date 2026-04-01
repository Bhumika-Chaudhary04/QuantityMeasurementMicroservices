package com.apps.qmaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementDTO {

	private String operation;
	private Double result;
	private String message;
	private boolean error;

	// success constructor
	public QuantityMeasurementDTO(String operation, Double result) {
		this.operation = operation;
		this.result = result;
		this.error = false;
	}

	// error constructor
	public QuantityMeasurementDTO(String operation, String message) {
		this.operation = operation;
		this.message = message;
		this.error = true;
	}
}