package proj2;

import java.util.ArrayList;

public class Stock {

    private String name;
    private Location location;
    private ArrayList<StockItem> item;
    private int countElectronic = 0, countGrocery = 0;

    public Stock() {//a no arg-constructor
        item = new ArrayList<>();//create new arrayList
        this.location = new Location("Lacasa Mall", "Arehan", "ramllah");//create new location
    }

    public Stock(String name) {//a parameterized constructor
        this.name = name;
        this.location = new Location("Lacasa Mall", "Arehan", "ramllah");//create new location
        item = new ArrayList<>();//create new arrayList
    }

    public String getName() {
        return name;
    }

    public ArrayList<StockItem> getItems() {//return the arrayList
        return item;
    }

    public void addStockItem(StockItem obj) {//a method to add items to the arrayList
        item.add(obj);
        if (obj instanceof Grocery)//check the type of the item
            countGrocery++;
        else if (obj instanceof Electronic)
            countElectronic++;
    }

    public int countElectronic() {//a method to return the number of electronic items
        return countElectronic;
    }

    public int countGrocery() {//a method to return the number of electronic items
        return countGrocery;
    }

    @Override
    public String toString() {//a toString method for the stock
        return "Stock{" +
                "name='" + name + '\'' +
                ", location=" + location.toString() +
                ", item=" + item +
                '}';
    }
}
