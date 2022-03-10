package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


public class ShoppingCart implements IShoppingCart {
    PriorityQueue<Item> orderedList = new PriorityQueue<>();
    Pricer pricer;
    private ReceiptFormat receiptFormat;


    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
        this.receiptFormat = ReceiptFormat.ITEM_QUANT_PRICE;
    }

    public ShoppingCart(Pricer pricer, ReceiptFormat receiptFormat) {
        this.pricer = pricer;
        this.receiptFormat = receiptFormat;
    }

    public void addItem(String itemType, int number) {
        orderedList.add(new Item(itemType, number));
    }

    /**
     * Prints the line on the receipt given the item and the price
     * @param item Name of the item
     * @param priceFloat the price of the items based on how many there are
     */

    private void printLine(Item item,float priceFloat) {
        switch (receiptFormat) {
            case PRICE_QUANT_ITEM:
                System.out.println(floatToString(priceFloat) + " - " + item.getQuantity() + " - " + item.getName());
                break;
        
            case ITEM_QUANT_PRICE:
            default:
                System.out.println(item.getName() + " - " + item.getQuantity() + " - " + floatToString(priceFloat));
                break;
        }
    }

    public void printReceipt() {
        float priceTotal = 0.0f;

        for (Item item : orderedList) {
            Integer price = pricer.getPrice(item.getName()) * item.getQuantity();
            Float priceFloat = new Float(new Float(price) / 100);
            priceTotal += (float)priceFloat;

            printLine(item,(float)priceFloat);
        }

        System.out.println("Total: " + floatToString(priceTotal));
    }

    public static String floatToString(float floatToConvert) {
        String outputString = String.format("€%.2f",floatToConvert);
        return outputString;
    }
}
