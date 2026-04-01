package com.apps.qmaservice.units;

import com.apps.qmaservice.exception.QuantityMeasurementException;

public enum TemperatureUnit implements IMeasurable {

	CELSIUS {
		@Override
		public double convertToBaseUnit(double value) {
			return value;
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value;
		}
	},

	FAHRENHEIT {
		@Override
		public double convertToBaseUnit(double value) {
			return (value - 32) * 5 / 9;
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value * 9 / 5 + 32;
		}
	},

	KELVIN {
		@Override
		public double convertToBaseUnit(double value) {
			return value - 273.15;
		}

		@Override
		public double convertFromBaseUnit(double value) {
			return value + 273.15;
		}
	};

	@Override
	public double getConversionFactor() {
		return 1.0;
	}

	@Override
	public String getUnitName() {
		return this.name();
	}

	@Override
	public void validateOperationSupport(String operation) {
		if (operation == null) {
			return;
		}

		String op = operation.trim().toLowerCase();

		if (!op.equals("convert") && !op.equals("compare")) {
			throw new QuantityMeasurementException("Temperature does not support " + operation + " operation");
		}
	}
}