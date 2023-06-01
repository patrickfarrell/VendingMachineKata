package com.grangeinsurance.kata.vendingmachine;

public enum Product {
	COLA(1.0d),
	CANDY(0.65d);

	private double unitCost;
	
	Product(double unitCost) {
		this.unitCost = unitCost;
	}
	
	public double getUnitCost() {
		return unitCost;
	}
}
