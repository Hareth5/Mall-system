package proj2;

public class Electronic extends StockItem implements Cloneable {

    private String version, type;
    private double power;

    public Electronic() {// a no arg-constructor
    }

    public Electronic(String version, String brand, double discount, double price, String type, double power) {//a parameterized constructor
        super(brand, discount, price);
        setVersion(version);
        setType(type);
        setPower(power);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double calculateTax() {//a method to calculate the tax of the electronic items
        return (getPrice() - getDiscount() / 100.0 * getPrice()) * 16.5 / 100.0;
    }


    @Override
    public Electronic clone() throws CloneNotSupportedException {//a method to clone the electronic attributes
        return (Electronic) super.clone();
    }

    @Override
    public String toString() {//a toString method for the electronic items
        return (getDiscount() > 0) ? getBrand() + " (" + getPrice() + ") with a discount of " + getDiscount() + "% (" + getType() + ") "
                + getPower() + " Watt" + ", after discount price is " + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice())) + ", after tax included (" + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice()) + calculateTax()) + ")."
                : getBrand() + " (" + getPrice() + ")" + " (" + getType() + ")"
                + ", (" + getPower() + " Watt" + "), after tax included (" + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice()) + calculateTax()) + ").";
    }
}
