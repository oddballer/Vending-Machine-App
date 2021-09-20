package com.techelevator;

import java.math.BigDecimal;

public class Chip extends Items {

    public Chip(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String itemPrint() {
        return "Crunch Crunch, Yum!";
    }

}