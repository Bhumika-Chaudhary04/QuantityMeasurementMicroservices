package com.apps.qmaservice.units;

public enum VolumeUnit implements IMeasurable {

	MILLILITRE(1.0), LITRE(1000.0), GALLON(3785.41);

	private final double factor;

	VolumeUnit(double factor) {
		this.factor = factor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return value * factor; // base = millilitres
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return baseValue / factor;
	}

	@Override
	public double getConversionFactor() {
		return factor;
	}

	@Override
	public String getUnitName() {
		return name();
	}
}