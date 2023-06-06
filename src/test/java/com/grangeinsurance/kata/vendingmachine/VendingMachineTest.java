package com.grangeinsurance.kata.vendingmachine;

import static org.assertj.core.api.Assertions.assertThat;

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
	private static final String THANK_YOU = "THANK YOU";
	private static final String TWENTY_FIVE_CENTS = "$0.25";
	private static final String TEN_CENTS = "$0.10";
	private static final String FIVE_CENTS = "$0.05";
	private static final String FORTY_CENTS = "$0.40";
	
	@Test
	void displayReadsInsertCoinWhenNoCoinsInserted() {
		assertThat(subject.getDisplayText()).isEqualTo(DEFAULT_TEXT);
	}
	
	@Test
	void displayReadsTwentyFiveCentsWhenOneQuarterInserted() {
		insertQuarter();
		assertThat(subject.getDisplayText()).isEqualTo(TWENTY_FIVE_CENTS);
	}
	
	@Test
	void displayReadsTenCentsWhenOneDimeInserted() {
		insertDime();
		assertThat(subject.getDisplayText()).isEqualTo(TEN_CENTS);
	}
	
	@Test
	void displayReadsFiveCentsWhenOneNickelInserted() {
		insertNickel();
		assertThat(subject.getDisplayText()).isEqualTo(FIVE_CENTS);
	}
	
	@Test
	void displayReadsFortyCentsWhenOneQuarterOneDimeAndOneNickelInserted() {
		insertQuarter();
		insertDime();
		insertNickel();
		assertThat(subject.getDisplayText()).isEqualTo(FORTY_CENTS);
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
		subject.dispenseCola();
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.COLA);
	}
	
	@Test
	void pickupBoxContainsCandyWhenSixtyFiveCentsInsertedAndDispenseCandyButtonPressed() {
		insertQuarter();
		insertQuarter();
		insertDime();
		insertNickel();
		subject.dispenseCandy();
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.CANDY);
	}
	
	@Test
	void pickupBoxContainsChipsWhenFiftyCentsInsertedAndDispenseChipsButtonPressed() {
		insertQuarter();
		insertQuarter();
		subject.dispenseChips();
		assertThat(subject.getPickupBox()).hasSize(1);
		assertThat(subject.getPickupBox()[0]).isEqualTo(Product.CHIPS);
	}
	
	@Test
	void displayReadsThankYouWhenProductIsDispensed() {
		insertQuarter();
		insertQuarter();
		subject.dispenseChips();
		assertThat(subject.getDisplayText()).isEqualTo(THANK_YOU);
	}
	
	@BeforeEach
	void initialize() {
		subject = new VendingMachine();
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