package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SaleTest {

    @Test
    public void testReturnChange() {
        Sale sale = new Sale();
        sale.setBalance(new BigDecimal("1.15"));

        String actualResult = sale.BigDecimalReturnChange("TestLog.txt");
        String expectedResult = "Your change is 4 quarters, 1 dimes, 1 nickels, and 0 pennies.";

        assertEquals(expectedResult, actualResult);

        BigDecimal actualBalance = sale.getBalance();
        BigDecimal expectedBalance = new BigDecimal("0.00");

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testFeedMoney() {
        Sale sale = new Sale();
        sale.feedMoney(new BigDecimal("5.00"), "TestLog.txt");
        sale.feedMoney(new BigDecimal("2.00"), "TestLog.txt");
        sale.feedMoney(new BigDecimal("1.00"), "TestLog.txt");

        BigDecimal actualResult = sale.getBalance();
        BigDecimal expectedResult = new BigDecimal("8.00");

        assertEquals(expectedResult, actualResult);
    }

}