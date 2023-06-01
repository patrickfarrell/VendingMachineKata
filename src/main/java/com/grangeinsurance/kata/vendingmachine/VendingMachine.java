package com.grangeinsurance.kata.vendingmachine;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	
	private float totalValue = 0;
	
	public String getDisplayText() {
		if (totalValue > .01) {
			return String.format("$%.2f", totalValue);
		}
		else {
			return DEFAULT_TEXT;
		}
	}

	public void insertCoin(float value) {
		totalValue += value;
	}
	
}