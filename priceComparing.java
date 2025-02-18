package proj2;

import java.util.Comparator;

public class priceComparing implements Comparator<StockItem> {

    // a class for the compare method that implements Comparator interface to compare two prices

    @Override
    public int compare(StockItem obj1, StockItem obj2) {
        return Double.compare(obj1.getPrice(), obj2.getPrice()) * -1;
    }
}
