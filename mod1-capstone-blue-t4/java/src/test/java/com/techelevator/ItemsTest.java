package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ItemsTest {

    @Test
    public void testDispenseItem() {
        Items testChips = new Chip("D4", "Crispy Potato Bois", new BigDecimal("1.50"), "chip");

        testChips.dispenseItem();

        String actualStockResult = testChips.getItemStock();
        String expectedStockResult = "(4 available)";

        assertEquals(expectedStockResult, actualStockResult);

        BigDecimal actualTotalResult = testChips.getItemTotal();
        BigDecimal expectedTotalResult = new BigDecimal("1.50");

        assertEquals(expectedTotalResult, actualTotalResult);
    }

    @Test
    public void testDispenseOutOfStock() {

        Items testChips = new Chip("D4", "Crispy Potato Bois", new BigDecimal("1.50"), "chip");

        testChips.dispenseItem();
        testChips.dispenseItem();
        testChips.dispenseItem();
        testChips.dispenseItem();
        testChips.dispenseItem();

        String actualStockResult = testChips.getItemStock();
        String expectedStockResult = "SOLD OUT";



        assertEquals(expectedStockResult, actualStockResult);

        BigDecimal actualTotalResult = testChips.getItemTotal();
        BigDecimal expectedTotalResult = new BigDecimal("7.50");

        assertEquals(expectedTotalResult, actualTotalResult);

    }

    @Test
    public void toStringTest() {

        Items testChips = new Chip("D4", "Crispy Potato Bois", new BigDecimal("1.50"), "chip");

        testChips.toString();

        String expectedString = "Slot |D4| Crispy Potato Bois $1.50 chip (5 available)";
        String actualString = testChips.toString();

        assertEquals(expectedString, actualString);

    }

}