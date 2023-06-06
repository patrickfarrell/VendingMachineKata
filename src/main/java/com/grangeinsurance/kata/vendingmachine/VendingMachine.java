package com.grangeinsurance.kata.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	private static final String PRODUCT_DISPENSED_TEXT = "THANK YOU";
	private static final String INSUFFICIENT_FUNDS_TEXT = "PRICE ";
	
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
			messages.push(INSUFFICIENT_FUNDS_TEXT + String.format("$%.2f", Product.COLA.getUnitCost()));
		}
	}

	public void dispenseCandy() {
		if (totalValue == Product.CANDY.getUnitCost()) {
			pickupBox.add(Product.CANDY);
			messages.push(PRODUCT_DISPENSED_TEXT);
			totalValue = 0;
		} else {
			messages.push(INSUFFICIENT_FUNDS_TEXT + String.format("$%.2f", Product.CANDY.getUnitCost()));
		}
	}
	
	public void dispenseChips() {
		if (totalValue >= Product.CHIPS.getUnitCost()) {
			pickupBox.add(Product.CHIPS);
			messages.push(PRODUCT_DISPENSED_TEXT);
			makeChange(Product.CHIPS);
			totalValue = 0;
		} else {
			messages.push(INSUFFICIENT_FUNDS_TEXT + String.format("$%.2f", Product.CHIPS.getUnitCost()));
		}
	}
	
	public Product[] getPickupBox() {
		return pickupBox.toArray(new Product[0]);
	}
	
	private void makeChange(Product product) {
		BigDecimal quarterValue = BigDecimal.valueOf(0.25d);
		BigDecimal dimeValue = BigDecimal.valueOf(0.10d);
		BigDecimal nickelValue = BigDecimal.valueOf(0.05d);
		
		// Calculate how much change should be returned
		BigDecimal change = BigDecimal.valueOf(totalValue).subtract(BigDecimal.valueOf(product.getUnitCost()));
		double totalChange = change.doubleValue();
		
		// Calculate how many quarters should be returned
		int quarters = ((change.multiply(BigDecimal.valueOf(100))).intValue()) / 25;
		change = change.subtract(BigDecimal.valueOf(quarters).multiply(quarterValue));
		
		// Calculate how many dimes should be returned
		int dimes = ((change.multiply(BigDecimal.valueOf(100))).intValue()) / 10;
		change = change.subtract(BigDecimal.valueOf(dimes).multiply(dimeValue));
		
		// Calculate how many nickels should be returned
		int nickels = ((change.multiply(BigDecimal.valueOf(100))).intValue()) / 5;
		change = change.subtract(BigDecimal.valueOf(nickels).multiply(nickelValue));
		
		System.out.println("Change: TotalChange: + " + String.format("$%.2f", totalChange) + " Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels);
		
		// Return the appropriate number of each coin
		returnCoins(quarters, 0.25d);
		returnCoins(dimes, 0.10d);
		returnCoins(nickels, 0.05d);
	}

	private void returnCoins(int qty, double value) {
		for (int i = 0; i < qty; i++) {
			coinReturn.add(value);
		}
		
	}
}