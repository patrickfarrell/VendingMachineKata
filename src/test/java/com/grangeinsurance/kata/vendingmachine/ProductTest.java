package com.grangeinsurance.kata.vendingmachine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	@Test
	void unitCostForColaIsOneDollar() {
		assertThat(Product.COLA.getUnitCost()).isEqualTo(1.0d);
	}
	
	@Test
	void unitCostForCandyIsSixtyFiveCents() {
		assertThat(Product.CANDY.getUnitCost()).isEqualTo(0.65d);
	}

	@Test
	void unitCostForChipsIsFiftyCents() {
		assertThat(Product.CHIPS.getUnitCost()).isEqualTo(0.50d);
	}
}
