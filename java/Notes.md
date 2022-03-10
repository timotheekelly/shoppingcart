# My Implementation

- Add a 'Total' line to the receipt. This should be the full price we should charge the customer.
    - I added a simple flaot priceTotal that increments as it prints the receipt, and then adds this total at the end 
- Make the receipt print items in the order that they were scanned
    - It did not specify, so I assumed if the same item was scanned in seperate clusters that these would appear as unique lines on the receipt. I used a PriorityQueue with a custom object as HashMaps don't maintain order and I sawe this as an efficient way of doing so while adding non unique items.
- In some branches of the store, customers want the receipt to show the price first on each line. Without changing the IShoppingCart interface, add a way to support this which allows for other formatting options in the future.
    - Using a swith case and declaring an enum, by default the receipt is printed with item first but this can be changed by creating a new ShoppingCart with the parameter ReceiptFormat.PRICE_QUANT_ITEM. This implementation will also allow addition of more formats with minimal changes to the code.
- One limitation of the codebase is that every time a change is made, many of the tests need updating. Update or replace the test suite to extend coverage and limit the number of tests which need updating when changes are introduced.
    - My addition of tests will allow for better coverage if the code base expands, they should not need as much updating for codebase changes as the original tests.