package com.grangeinsurance.kata.vendingmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class VendingMachine {

	private static final String DEFAULT_TEXT = "INSERT COIN";
	private static final String PRODUCT_DISPENSED_TEXT = "THANK YOU";
	private static final String INSUFFICIENT_FUNDS_TEXT = "PRICE ";
	private static final String OUT_OF_STOCK_TEXT = "SOLD OUT";
	private static final String EXACT_CHANGE_TEXT = "EXACT CHANGE ONLY";
	
	private double totalValue;
	private Set<Double> validCoins = new HashSet<Double>();
	private List<Double> insertedCoins = new ArrayList<Double>();
	private List<Double> coinReturn = new ArrayList<Double>();
	private List<Product> pickupBox = new ArrayList<Product>();
	private Stack<String> messages = new Stack<String>();
	private Map<Product, Integer> productInventory = new HashMap<Product,Integer>();
	private boolean exactChange = false;
	
	public VendingMachine() {
		validCoins.add(0.25d);
		validCoins.add(0.10d);
		validCoins.add(0.05d);
	}
	
	public VendingMachine(Map<Product, Integer> productInventory) {
		this();
		this.productInventory = productInventory;
	}
	
	public VendingMachine(Map<Product, Integer> productInventory, boolean exactChange) {
		this(productInventory);
		this.exactChange = exactChange;
	}
	
	public String getDisplayText() {
		if (messages.isEmpty()) {
			if (totalValue > 0) {
				return String.format("$%.2f", totalValue);
			} else {
				if (exactChange) {
					return EXACT_CHANGE_TEXT;
				} else {
					return DEFAULT_TEXT;
				}
			}
		} else {
			return messages.pop();
		}
	}
	
	public void insertCoin(double value) {
		if (validCoins.contains(value)) {
			insertedCoins.add(value);
			totalValue += value;
		} else {
			coinReturn.add(value);
		}
	}
	
	public Double[] getCoinReturn() {
		return coinReturn.toArray(new Double[0]);
	}
	
	public void dispense(Product product) {
		if (productInventory.containsKey(product)) {
			if (totalValue >= product.getUnitCost()) {
				pickupBox.add(product);
				messages.push(PRODUCT_DISPENSED_TEXT);
				makeChange(BigDecimal.valueOf(totalValue).subtract(BigDecimal.valueOf(product.getUnitCost())).doubleValue());
				insertedCoins.clear();
				totalValue = 0;
			} else {
				messages.push(INSUFFICIENT_FUNDS_TEXT + String.format("$%.2f", product.getUnitCost()));
			}
		} else {
			messages.push(OUT_OF_STOCK_TEXT);
		}
	}
	
	public Product[] getPickupBox() {
		return pickupBox.toArray(new Product[0]);
	}	
	
	public void returnCoins() {
		coinReturn.addAll(insertedCoins);
		insertedCoins.clear();
	}
	
	private void makeChange(double change) {
		BigDecimal quarterValue = BigDecimal.valueOf(0.25d);
		BigDecimal dimeValue = BigDecimal.valueOf(0.10d);
		BigDecimal nickelValue = BigDecimal.valueOf(0.05d);
		BigDecimal bdChange = BigDecimal.valueOf(change);
		
		// Calculate how many quarters should be returned
		int quarters = ((bdChange.multiply(BigDecimal.valueOf(100))).intValue()) / 25;
		bdChange = bdChange.subtract(BigDecimal.valueOf(quarters).multiply(quarterValue));
		
		// Calculate how many dimes should be returned
		int dimes = ((bdChange.multiply(BigDecimal.valueOf(100))).intValue()) / 10;
		bdChange = bdChange.subtract(BigDecimal.valueOf(dimes).multiply(dimeValue));
		
		// Calculate how many nickels should be returned
		int nickels = ((bdChange.multiply(BigDecimal.valueOf(100))).intValue()) / 5;
		bdChange = bdChange.subtract(BigDecimal.valueOf(nickels).multiply(nickelValue));
		
		System.out.println("Change: TotalChange: + " + String.format("$%.2f", change) + " Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels);
		
		// Return the appropriate number of each coin
		addCoinsToCoinReturn(quarters, 0.25d);
		addCoinsToCoinReturn(dimes, 0.10d);
		addCoinsToCoinReturn(nickels, 0.05d);
	}

	private void addCoinsToCoinReturn(int qty, double value) {
		for (int i = 0; i < qty; i++) {
			coinReturn.add(value);
		}
		
	}	
}