package com.grangeinsurance.kata.vendingmachine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class ensures that the Vending Machine is 
 * operating according to the given specifications
 * 
 * Approach:
 * 1. Write a failing test
 * 2. Make the test pass
 * 3. Commit
 * 4. Refactor
 * 5. Commit
 * 
 * @author Patrick Farrell
 */
class VendingMachineTest {
	
	private VendingMachine subject;
	private static final String DEFAULT_TEXT = "INSERT COIN";
	private static final String PRODUCT_DISPENSED_TEXT = "THANK YOU";
	private static final String INSUFFICIENT_FUNDS_TEXT = "PRICE ";
	private static final String OUT_OF_STOCK_TEXT = "SOLD OUT";
	
	@Test
	void displayReadsInsertCoinWhenNoCoinsInserted() {
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void displayReadsTwentyFiveCentsWhenOneQuarterInserted() {
		insertQuarter();
		assertThat(subject.getDisplayText()).isEqualTo("$0.25");
	}
	
	@Test
	void displayReadsTenCentsWhenOneDimeInserted() {
		insertDime();
		assertThat(subject.getDisplayText()).isEqualTo("$0.10");
	}
	
	@Test
	void displayReadsFiveCentsWhenOneNickelInserted() {
		insertNickel();
		assertThat(subject.getDisplayText()).isEqualTo("$0.05");
	}
	
	@Test
	void displayReadsFortyCentsWhenOneQuarterOneDimeAndOneNickelInserted() {
		insertQuarter();
		insertDime();
		insertNickel();
		assertThat(subject.getDisplayText()).isEqualTo("$0.40");
	}
	
	@Test
	void displayReadsInsertCoinWhenOnePennyInserted() {
		insertPenny();
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void coinReturnContainsOnePennyWhenOneWhenOnePennyInserted() {
		insertPenny();
		assertThat(subject.getCoinReturn()).hasSize(1);
		assertThat(subject.getCoinReturn()[0]).isEqualTo(.01d);
	}
	
	@Test
	void pickupBoxContainsColaWhenOneDollarInsertedAndDispenseColaButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.COLA);
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.COLA);
	}
	
	@Test
	void pickupBoxContainsCandyWhenSixtyFiveCentsInsertedAndDispenseCandyButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertDime();
		insertNickel();
		subject.dispense(Product.CANDY);
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.CANDY);
	}
	
	@Test
	void pickupBoxContainsChipsWhenFiftyCentsInsertedAndDispenseChipsButtonPressed() {
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.CHIPS);
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.CHIPS);
	}
	
	@Test
	void displayReadsThankYouWhenProductIsDispensed() {
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.CHIPS);
		assertThat(subject.getDisplayText()).isEqualTo(PRODUCT_DISPENSED_TEXT);
	}
	
	@Test 
	void displayReadsInsertCoinAfterTemporarilyThankingTheUserForTheirPurchase() {
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.CHIPS);
		subject.getDisplayText(); // THANK YOU
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void displayReadsOneDollarWhenFiftyCentsInsertedAndDispenseColaButtonPressed() {
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.COLA);
		assertThat(subject.getDisplayText()).isEqualTo(INSUFFICIENT_FUNDS_TEXT + "$1.00");
		assertThat(subject.getDisplayText()).isEqualTo("$0.50");
	}
	
	@Test
	void displayReadsSixtyFiveCentsWhenNoCoinsInsertedAndDispenseCandyButtonPressed() {
		subject.dispense(Product.CANDY);
		assertThat(subject.getDisplayText()).isEqualTo(INSUFFICIENT_FUNDS_TEXT + "$0.65");
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void displayReadsFiftyCentsWhenTwentyFiveCentsInsertedAndDispenseChipsButtonPressed() {
		insertQuarter();
		subject.dispense(Product.CHIPS);
		assertThat(subject.getDisplayText()).isEqualTo(INSUFFICIENT_FUNDS_TEXT + "$0.50");
		assertThat(subject.getDisplayText()).isEqualTo("$0.25");
	}
	
	@Test
	void coinReturnContainsFortyCentsWhenNinetyCentsInsertedAndDispenseChipsButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertQuarter();
		insertDime();
		insertNickel();
		subject.dispense(Product.CHIPS);
		assertThat(subject.getCoinReturn()).hasSize(3);
		assertThat(subject.getCoinReturn()[0]).isEqualTo(0.25d);
		assertThat(subject.getCoinReturn()[1]).isEqualTo(0.10d);
		assertThat(subject.getCoinReturn()[2]).isEqualTo(0.05d);
	}
	
	@Test
	void coinReturnContainsTenCentsWhenSeventyFiveCentsInsertedAndDispenseCandyButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertQuarter();
		subject.dispense(Product.CANDY);
		assertThat(subject.getCoinReturn()).hasSize(1);
		assertThat(subject.getCoinReturn()[0]).isEqualTo(0.10d);
	}
	
	@Test
	void coinReturnContainsFiveCentsWhenOneDollarFiveCentsInsertedAndDispenseColaButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertQuarter();
		insertDime();
		insertDime();
		insertNickel();
		insertNickel();
		subject.dispense(Product.COLA);
		assertThat(subject.getCoinReturn()).hasSize(1);
		assertThat(subject.getCoinReturn()[0]).isEqualTo(0.05d);
	}
	
	@Test
	void coinReturnContainsThirtyCentsWhenOneQuarterAndOneNickelInsertedAndReturnCoinsButtonPressed() {
		insertQuarter();
		insertNickel();
		subject.returnCoins();
		assertThat(subject.getCoinReturn()).hasSize(2);
		assertThat(subject.getCoinReturn()[0]).isEqualTo(0.25d);
		assertThat(subject.getCoinReturn()[1]).isEqualTo(0.05d);
	}
	
	@Test
	void displayReadsSoldOutFollowedByInsertCoinWhenProductIsSelectedAndNoCoinsInserted() {
		subject.setProductInventory(new HashMap<Product,Integer>());
		subject.dispense(Product.COLA);
		assertThat(subject.getDisplayText()).isEqualTo(OUT_OF_STOCK_TEXT);
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void displayReadsSoldOutFollowedByValueOfCoinsInsertedWhenProductIsSelected() {
		subject.setProductInventory(new HashMap<Product,Integer>());
		insertQuarter();
		subject.dispense(Product.COLA);
		assertThat(subject.getDisplayText()).isEqualTo(OUT_OF_STOCK_TEXT);
		assertThat(subject.getDisplayText()).isEqualTo("$0.25");
	}
	
	@BeforeEach
	void initialize() {
		subject = new VendingMachine();
		Map<Product, Integer> productInventory = new HashMap<Product,Integer>();
		productInventory.put(Product.COLA, 1);
		productInventory.put(Product.CANDY, 1);
		productInventory.put(Product.CHIPS, 1);
		subject.setProductInventory(productInventory);
	}
	
	private void insertQuarter() {
		subject.insertCoin(0.25d);
	}
	
	private void insertDime() {
		subject.insertCoin(0.10d);
	}
	
	private void insertNickel() {
		subject.insertCoin(0.05d);
	}
	
	private void insertPenny() {
		subject.insertCoin(0.01d);
	}
}