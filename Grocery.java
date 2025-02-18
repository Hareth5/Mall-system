package proj2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Grocery extends StockItem implements Cloneable {

    private String name;
    private Calendar expiryDate;
    private float weight;

    public Grocery() {//a no arg-constructor
    }

    public Grocery(String name, String brand, double discount, double price, Calendar expirydate, float weight) {//a parameterized constructor
        super(brand, discount, price);
        setName(name);
        setExpirydate(expirydate);
        setWeight(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpirydate() {
        return expiryDate;
    }

    public void setExpirydate(Calendar expirydate) {
        this.expiryDate = expirydate;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public double calculateTax() {//a method to calculate the tax of the grocery items
        return (getPrice() - getDiscount() / 100.0 * getPrice()) * 5.75 / 100.0;
    }

    public static int dateComparing(Calendar cal) {// a method to compare two dates using compareTo method

        return cal.compareTo(Calendar.getInstance()) * -1;
    }

    public static boolean isDateAvailable(int year, int month, int day) {//a method to check the date is available or not
        if (month < 0 || month > 11) {
            return false;
        }

        if (day < 1 || day > new GregorianCalendar(year, month, 1).getActualMaximum(Calendar.DAY_OF_MONTH)) {//check the day of the month if availabile or not
            return false;
        }

        Calendar cl = new GregorianCalendar(year, month, day);
        if (dateComparing(cl) > 0) {//check if the date is expired of not
            return false;
        }
        return true;
    }

    @Override
    public Grocery clone() throws CloneNotSupportedException {//a method to copy the grocery attributes and give the object expiryDate a new reference

        Grocery cloned = (Grocery) super.clone();
        cloned.expiryDate = (Calendar) this.expiryDate.clone();
        return cloned;
    }

    @Override
    public String toString() {//a toString method for the grocery items
        return (getDiscount() > 0) ? getBrand() + " (" + getPrice() + ") has a discount of " + getDiscount() + "%, expiry date "
                + getExpirydate().get(Calendar.DAY_OF_MONTH) + '/' + (getExpirydate().get(Calendar.MONTH) + 1) + '/' + getExpirydate().get(Calendar.YEAR)
                + " (" + getWeight() + "KG), after discount price is " + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice())) + ", after tax included (" + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice()) + calculateTax()) + ")."

                : getBrand() + " (" + getPrice() + "), expiry date " + getExpirydate().get(Calendar.DAY_OF_MONTH) + '/'
                + (getExpirydate().get(Calendar.MONTH) + 1) + '/' + getExpirydate().get(Calendar.YEAR)
                + ", after tax included (" + String.format("%.2f", (getPrice() - getDiscount() / 100.0 * getPrice()) + calculateTax()) + ").";
    }
}
