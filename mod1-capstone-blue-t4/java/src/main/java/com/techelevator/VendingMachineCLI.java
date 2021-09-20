package com.techelevator;

import com.techelevator.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private Sale sale = new Sale();
	private BigDecimal currentBalance = sale.getBalance();
	private String menuBalanceString = "Finish Transaction\n\n" + "Current Balance: \\$" + currentBalance;

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT = "THIS IS NOT AN OPTION. IT IS SECRET. DO NOT SELECT.";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_REPORT };
	private String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", menuBalanceString};
	private static final String[] FEED_MONEY_OPTIONS = {"$1", "$2", "$5", "$10", "Done"};

	private static final BigDecimal oneDollar = new BigDecimal("1.00");
	private static final BigDecimal twoDollars = new BigDecimal("2.00");
	private static final BigDecimal fiveDollars = new BigDecimal("5.00");
	private static final BigDecimal tenDollars = new BigDecimal("10.00");

	private String logFile = "Log.txt";

	private Menu menu;
	private List<Items> vendingMachineItems = new ArrayList<Items>();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		loadItems();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchaseMenu();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you for snacking!");
				System.exit(0);
			} else if (choice.equals(MAIN_MENU_OPTION_REPORT)) {
				displaySalesReport();
			}
		}
	}

	public void displayPurchaseMenu(){

		boolean stay = true;

		while (stay) {

			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choice.equals("Feed Money")) {
				feedMoneyMenu();

			} else if (choice.equals("Select Product")) {
				purchaseItems();

			} else if (choice.equals(menuBalanceString)) {
				System.out.println(sale.BigDecimalReturnChange(logFile));

				LogWriter log = new LogWriter(logFile);
				log.logSales(">\\'\\'\\'");

				stay = false;
			}

		}

	}

	public void loadItems() {

		File inventory = new File("vendingmachine.csv");

		try {
			Scanner inventoryScanner = new Scanner(inventory);

			while (inventoryScanner.hasNextLine()) {

				String line = inventoryScanner.nextLine();
				String[] lineArr = line.split("\\|");
				String itemSlot = lineArr[0];
				String itemName = lineArr[1];
				String itemPrice = lineArr[2];
				String itemType = lineArr[3];


				if (lineArr[3].equals("Candy")) {

					Items candy = new Candy(itemSlot, itemName, new BigDecimal(itemPrice), itemType);
					vendingMachineItems.add(candy);

				}

				else if (lineArr[3].equals("Drink")) {

					Items drink = new Drink(itemSlot, itemName, new BigDecimal(itemPrice), itemType);
					vendingMachineItems.add(drink);

				}

				else if (lineArr[3].equals("Gum")) {

					Items gum = new Gum(itemSlot, itemName, new BigDecimal(itemPrice), itemType);
					vendingMachineItems.add(gum);

				}

				else if (lineArr[3].equals("Chip")) {

					Items chip = new Chip(itemSlot, itemName, new BigDecimal(itemPrice), itemType);
					vendingMachineItems.add(chip);

				}


			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void feedMoneyMenu() {
		boolean stay = true;

		while (stay){
			String choice = (String) menu.getChoiceFromOptions(FEED_MONEY_OPTIONS);
			if (choice.equals("$1")) {
				sale.feedMoney(oneDollar, logFile);
			}

			else if (choice.equals("$2")){
				sale.feedMoney(twoDollars, logFile);
			}

			else if (choice.equals("$5")) {
				sale.feedMoney(fiveDollars, logFile);
			}

			else if (choice.equals("$10")) {
				sale.feedMoney(tenDollars, logFile);
			}

			else if (choice.equals("Done")) {
				menuBalanceString = "Finish Transaction\n\n" + "Current Balance: $" + sale.getBalance();
				PURCHASE_MENU_OPTIONS = new String[]{"Feed Money", "Select Product", menuBalanceString};
				stay = false;
			}

		}

	}

	public void purchaseItems() {

		System.out.println("Enter the slot code of your selected item: ");
		Scanner purchaseScanner = new Scanner(System.in);
		String purchaseSelection = purchaseScanner.nextLine().toUpperCase();

		boolean found = false;

		for (Items item : vendingMachineItems) {
			if (item.getItemSlot().equals(purchaseSelection)) {
				boolean moneyCheck = sale.getBalance().compareTo(item.getItemPrice()) >= 0;
				found = true;

				if (item.getItemStock().equals("SOLD OUT")) {
					System.out.println(item.getItemStock());
				}

				else if (!moneyCheck) {
					System.out.println("Insufficient funds. Give me more money.");
				}

				else {
					sale.purchaseItem(item, logFile);
					menuBalanceString = "Finish Transaction\n\n" + "Current Balance: $" + sale.getBalance();
					PURCHASE_MENU_OPTIONS = new String[]{"Feed Money", "Select Product", menuBalanceString};
					System.out.println("Remaining funds: $" + sale.getBalance());
					System.out.println(item.getItemStock());
				}
			}
		}

		if (!found) {
			System.out.println("Invalid slot choice. Returning to purchase menu.");
		}

	}

	public void displayItems() {

		for (Items item : this.vendingMachineItems) {
			System.out.println(item.toString());
		}

	}

	public void displaySalesReport() {
		LogWriter log = new LogWriter();
		BigDecimal salesReportTotal = new BigDecimal("0.00");

		for (Items item : vendingMachineItems) {
			salesReportTotal = salesReportTotal.add(item.getItemTotal());
			log.logSales(item.getItemName() + "|" + item.getItemTotal());
		}

		log.logSales("\nTOTAL SALES: $" + salesReportTotal);
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
