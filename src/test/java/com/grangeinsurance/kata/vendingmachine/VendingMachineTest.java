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
	
	@Test
	void displayReadsInsertCoinWhenNoCoinsInserted() {
		VendingMachine subject = new VendingMachine();
		assertThat(subject.getDisplayText()).isEqualTo("INSERT COIN");
	}
}