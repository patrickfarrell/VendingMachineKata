package com.grangeinsurance.kata.vendingmachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	private static final String PRODUCT_DISPENSED_TEXT = "THANK YOU";
	
	private double totalValue;
	private Set<Double> validCoins = new HashSet<Double>();
	private List<Double> coinReturn = new ArrayList<Double>();
	private List<Product> pickupBox = new ArrayList<Product>();
	private Stack<String> messages = new Stack<String>();
	
	public VendingMachine() {
		validCoins.add(0.25d);
		validCoins.add(0.10d);
		validCoins.add(0.05d);
	}
	
	public String getDisplayText() {
		if (messages.isEmpty()) {
			if (totalValue > 0) {
				return String.format("$%.2f", totalValue);
			} else {
				return DEFAULT_TEXT;	
			}
		} else {
			return messages.pop();
		}
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
			messages.push(PRODUCT_DISPENSED_TEXT);
			totalValue = 0;
		} else {
			messages.push("PRICE $1.00");
		}
	}

	public void dispenseCandy() {
		if (totalValue == Product.CANDY.getUnitCost()) {
			pickupBox.add(Product.CANDY);
			messages.push(PRODUCT_DISPENSED_TEXT);
			totalValue = 0;
		}
	}
	
	public void dispenseChips() {
		if (totalValue == Product.CHIPS.getUnitCost()) {
			pickupBox.add(Product.CHIPS);
			messages.push(PRODUCT_DISPENSED_TEXT);
			totalValue = 0;
		}
	}
	
	public Product[] getPickupBox() {
		return pickupBox.toArray(new Product[0]);
	}
}