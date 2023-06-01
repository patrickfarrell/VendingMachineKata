package com.grangeinsurance.kata.vendingmachine;

public enum Product {
	COLA(1.0f);

	private float unitCost;
	
	Product(float unitCost) {
		this.unitCost = unitCost;
	}
	
	public float getUnitCost() {
		return unitCost;
	}
}
