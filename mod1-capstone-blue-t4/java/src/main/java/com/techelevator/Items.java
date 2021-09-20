package com.techelevator;

import java.math.BigDecimal;

public abstract class Items {

    private String itemSlot;
    private String itemName;
    private BigDecimal itemPrice;
    private String itemType;
    private int itemStock;
    private BigDecimal itemTotal = new BigDecimal("0.00");

    public Items(String itemSlot, String itemName, BigDecimal itemPrice, String itemType) {
        this.itemSlot = itemSlot;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemStock = 5;
    }

    public String getItemSlot() {
        return itemSlot;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemStock() {

        if (this.itemStock > 0) {

            return ("(" + itemStock + " available)");
        }

        return "SOLD OUT";
    }

    public void dispenseItem() {
        this.itemStock--;
        this.itemTotal = itemTotal.add(itemPrice);
        System.out.println(this.itemName + ": $" + this.itemPrice);
        System.out.println(itemPrint());
    }

    @Override
    public String toString() {
        return "Slot " + "|" + getItemSlot() + "| " + getItemName() + " $" +getItemPrice() + " " + getItemType() + " " + getItemStock();
    }

    public abstract String itemPrint();

    public BigDecimal getItemTotal() {
        return itemTotal;
    }
}
