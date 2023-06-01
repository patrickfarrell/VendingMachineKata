package com.grangeinsurance.kata.vendingmachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	
	private float totalValue;
	private Set<Float> validCoins = new HashSet<Float>();
	private List<Float> coinReturn = new ArrayList<Float>();
	private List<Product> pickupBox = new ArrayList<Product>();
	
	public VendingMachine() {
		validCoins.add(0.25f);
		validCoins.add(0.10f);
		validCoins.add(0.05f);
	}
	
	public String getDisplayText() {
		if (totalValue > 0) {
			return String.format("$%.2f", totalValue);
		}
		return DEFAULT_TEXT;
	}
	
	public void insertCoin(float value) {
		if (validCoins.contains(value)) {
			totalValue += value;
		} else {
			coinReturn.add(value);
		}
	}
	
	public Float[] getCoinReturn() {
		return coinReturn.toArray(new Float[0]);
	}

	public void dispenseCola() {
		if (totalValue == Product.COLA.getUnitCost()) {
			pickupBox.add(Product.COLA);
		}
	}

	public Product[] getPickupBox() {
		return pickupBox.toArray(new Product[0]);
	}
}