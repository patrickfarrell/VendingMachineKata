package com.grangeinsurance.kata.vendingmachine;

import java.util.HashSet;
import java.util.Set;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	
	private float totalValue = 0;
	
	private Set<Float> validCoins = new HashSet<Float>();
	
	public VendingMachine() {
		validCoins.add(0.25f);
		validCoins.add(0.10f);
		validCoins.add(0.05f);
	}
	
	
	public String getDisplayText() {
		if (totalValue > 0) {
			return String.format("$%.2f", totalValue);
		}
		else {
			return DEFAULT_TEXT;
		}
	}

	public void insertCoin(float value) {
		if (validCoins.contains(value)) {
			totalValue += value;
		}
	}
	
}