package com.udacity.cart.service;

import com.udacity.cart.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TotalsCalculatorTest {

    @Test
    public void getTotals_givenMultipleItems_sumsPriceAndTax() {
        // Arrange
        TotalsCalculator totalsCalculator = new TotalsCalculator();

        List<CartItem> itemList = List.of(
                new CartItem("Soda", 3.00, 0.50),
                new CartItem("Small peperoni pizza", 6.00, 0.60),
                new CartItem("Fries", 2.00, 0.10)
        );

        // Act
        CartTotals totals = totalsCalculator.getTotals(itemList);

        // Assert
        assertAll("Totals match",
                () -> assertEquals(11.0, totals.getSubtotal(), 0.001),
                () -> assertEquals(1.2, totals.getTaxes(), 0.001)
        );
    }
}
