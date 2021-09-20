package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Items {

    public Drink(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String itemPrint() {
        return "Glug Glug, Yum!";
    }
}
