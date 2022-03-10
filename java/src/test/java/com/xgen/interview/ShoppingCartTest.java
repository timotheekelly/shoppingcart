package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {

    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");
        
        assertEquals(String.format("apple - 1 - €1.00%n"), receiptSample[0]+"\n");
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");

        assertEquals(String.format("apple - 2 - €2.00%n"), receiptSample[0]+"\n");
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");

        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%n"), receiptSample[0]+"\n"+receiptSample[1]+"\n");

    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");
        
        assertEquals(String.format("crisps - 2 - €0.00%n"), receiptSample[0]+"\n");
    }

    @Test
    public void makeTotalLineOnReceipt() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        
        String result = myOut.toString();

        assertEquals(String.format("apple - 1 - €1.00%nTotal: €1.00%n"), result);
    }

    @Test 
    public void canPrintItemsInOrderScannedWhileScannedTogether() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");
        
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%n"), receiptSample[0]+"\n"+receiptSample[1]+"\n");
    }

    @Test 
    public void canPrintItemsInOrderScannedWhileScannedApart() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");
        
        assertEquals(String.format("apple - 2 - €2.00%nbanana - 1 - €2.00%napple - 2 - €2.00%n"), receiptSample[0]+"\n"+receiptSample[1]+"\n"+receiptSample[2]+"\n");
    }

    @Test
    public void canDeclareDefaultFormattingOfReceiptWithoutBreaing() {
        ShoppingCart sc = new ShoppingCart(new Pricer(),ReceiptFormat.ITEM_QUANT_PRICE);

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");

        assertEquals(String.format("apple - 1 - €1.00%n"), receiptSample[0]+"\n");
    }

    @Test
    public void canChangeFormattingOfReceipt() {
        ShoppingCart sc = new ShoppingCart(new Pricer(),ReceiptFormat.PRICE_QUANT_ITEM);

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        
        sc.printReceipt();
        String myOutString = myOut.toString();
        String[] receiptSample = myOutString.split("\n");

        assertEquals(String.format("€1.00 - 1 - apple%n"), receiptSample[0]+"\n");
    }
}


