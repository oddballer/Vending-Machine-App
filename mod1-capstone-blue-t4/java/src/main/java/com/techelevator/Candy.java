package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Items {

    public Candy(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        super(itemSlot, itemName, itemPrice, itemType);
    }

    @Override
    public String itemPrint() {
        return "Munch Munch, Yum!";
    }

}
