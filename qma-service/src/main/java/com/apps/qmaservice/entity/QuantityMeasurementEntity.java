package com.apps.qmaservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantity_measurements")
@Data
@NoArgsConstructor
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String operation;

	private double operand1;
	private double operand2;

	private double result;

	private boolean error;

	private String errorMessage;

	public QuantityMeasurementEntity(String operation, double op1, double op2, double result) {
		this.operation = operation;
		this.operand1 = op1;
		this.operand2 = op2;
		this.result = result;
		this.error = false;
	}

	public QuantityMeasurementEntity(String operation, String errorMessage) {
		this.operation = operation;
		this.error = true;
		this.errorMessage = errorMessage;
	}
}