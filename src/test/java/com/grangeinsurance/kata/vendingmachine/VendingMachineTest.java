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
	 
	 @Test
	 void vendingMachineExists() {
		 assertThat(subject).isNotNull();
	 }
	 
	 @Test
	 void displayReadsInsertCoinWhenNoCoinsInserted() {
		 assertThat(subject.getDisplayText()).isEqualTo("INSERT COIN");
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
		 assertThat(subject.getDisplayText()).isEqualTo("INSERT COIN");
	 }
	 
	 @BeforeEach
	 void initialize() {
		 subject = new VendingMachine();
	 }
	 
	 private void insertQuarter() {
		 subject.insertCoin(0.25f);
	 }
	 
	 private void insertDime() {
		 subject.insertCoin(0.10f);
	 }
	 
	 private void insertNickel() {
		 subject.insertCoin(0.05f);
	 }
	 
	 private void insertPenny() {
		 subject.insertCoin(0.01f);
	 }
}
