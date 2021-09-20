package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Items {

    public Gum(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String itemPrint() {
        return "Chew Chew, Yum!";
    }

}
