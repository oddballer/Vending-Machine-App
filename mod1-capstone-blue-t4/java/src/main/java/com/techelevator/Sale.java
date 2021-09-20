package com.techelevator;

import java.math.BigDecimal;

public class Sale {
    private BigDecimal balance;

    public Sale() {
        this.balance = new BigDecimal("0.00");
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void feedMoney(BigDecimal fedBill, String logFile) {
        BigDecimal currentBalance = this.balance;
        BigDecimal newBalance = this.balance.add(fedBill);

        LogWriter log = new LogWriter(logFile);
        String logString = " FEED MONEY: \\$" + currentBalance + " \\$" + newBalance;

        log.logTransaction(logString);
        this.balance = newBalance;
    }

    public void purchaseItem(Items item, String logFile) {
        BigDecimal currentBalance = this.balance;
        BigDecimal newBalance = this.balance.subtract(item.getItemPrice());

        LogWriter log = new LogWriter(logFile);
        String logString = " " + item.getItemName() + " \\$" + currentBalance + " \\$" + newBalance;

        log.logTransaction(logString);
        this.balance = newBalance;
        item.dispenseItem();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String BigDecimalReturnChange(String logFile) {

        BigDecimal currentBalance = this.getBalance();
        int change = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        String str = "Your change is ";

        System.out.println("Change: $" + this.getBalance());

        BigDecimal bigChange = currentBalance.multiply(new BigDecimal("100"));
        change = bigChange.intValue();

        while (change > 0) {

            if (change >= 25) {
                change -= 25;
                quarters++;
            }
            else if (change >= 10) {
                change -= 10;
                dimes++;
            }
            else if (change >= 5) {
                change -= 5;
                nickels++;
            }
            else {
                pennies = change;
                change = 0;
            }
        }

        str = str + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, and " + pennies + " pennies.";
        LogWriter log = new LogWriter(logFile);

        String logString = " RETURN CHANGE: \\$" + currentBalance + " $0.00";
        log.logTransaction(logString);

        this.balance = new BigDecimal("0.00");
        return str;
    }
}
