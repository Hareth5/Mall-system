package proj2;

public abstract class StockItem implements Comparable<StockItem>, Cloneable {

    protected String brand;
    protected double discount, price;

    public StockItem() {//a no arg-constructor
    }

    public StockItem(String brand, double discount, double price) {//a parameterized constructor
        setBrand(brand);
        setDiscount(discount);
        setPrice(price);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {//a method to set the discount of items
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double calculateTax();//an abstract method to calculate the tax

    @Override
    public boolean equals(Object obj) {//an equals method to check id tow object have the same values or not
        if (obj instanceof StockItem)
            return (this.getBrand().equals(((StockItem) obj).getBrand()) && this.getPrice() == ((StockItem) obj).getPrice()
                    && this.getDiscount() == ((StockItem) obj).getDiscount());
        return false;
    }

    @Override
    public int compareTo(StockItem obj) {//override the comareTo method from Comparable interface
        return this.getBrand().compareTo(obj.getBrand()) * -1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {//a method to clone the StockItem attributes
        return super.clone();
    }

    @Override
    public String toString() {//a toString method for StockItems
        return (getDiscount() > 0) ? getBrand() + " " + '(' + getPrice() + "$) " + "has a discount of " + getDiscount() + "%."
                : getBrand() + " " + '(' + getPrice() + "$).";
    }
}


