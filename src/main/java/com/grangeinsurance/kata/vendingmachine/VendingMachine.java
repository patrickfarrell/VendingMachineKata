package com.grangeinsurance.kata.vendingmachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	
	private double totalValue;
	private Set<Double> validCoins = new HashSet<Double>();
	private List<Double> coinReturn = new ArrayList<Double>();
	private List<Product> pickupBox = new ArrayList<Product>();
	
	public VendingMachine() {
		validCoins.add(0.25d);
		validCoins.add(0.10d);
		validCoins.add(0.05d);
	}
	
	public String getDisplayText() {
		if (totalValue > 0) {
			return String.format("$%.2f", totalValue);
		}
		return DEFAULT_TEXT;
	}
	
	public void insertCoin(double value) {
		if (validCoins.contains(value)) {
			totalValue += value;
		} else {
			coinReturn.add(value);
		}
	}
	
	public Double[] getCoinReturn() {
		return coinReturn.toArray(new Double[0]);
	}

	public void dispenseCola() {
		if (totalValue == Product.COLA.getUnitCost()) {
			pickupBox.add(Product.COLA);
		}
	}

	public void dispenseCandy() {
		if (totalValue == Product.CANDY.getUnitCost()) {
			pickupBox.add(Product.CANDY);
		}
	}
	
	public void dispenseChips() {
		if (totalValue == Product.CHIPS.getUnitCost()) {
			pickupBox.add(Product.CHIPS);
		}
	}
	
	public Product[] getPickupBox() {
		return pickupBox.toArray(new Product[0]);
	}
}