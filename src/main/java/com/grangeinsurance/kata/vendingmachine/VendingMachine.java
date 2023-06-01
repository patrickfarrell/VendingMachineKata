package com.grangeinsurance.kata.vendingmachine;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	
	private float totalValue;
	
	public String getDisplayText() {
		if (totalValue > 0) {
			return String.format("$%.2f", totalValue);
		}
		return DEFAULT_TEXT;
	}
	
	public void insertCoin(float value) {
		totalValue = value;
	}
}