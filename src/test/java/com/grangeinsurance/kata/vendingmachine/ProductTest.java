package com.grangeinsurance.kata.vendingmachine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ProductTest {
	
	@Test
	void unitCostForColaIsOneDollar() {
		assertThat(Product.COLA.getUnitCost()).isEqualTo(1.0f);
	}

}
