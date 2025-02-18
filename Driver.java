package proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;


public class Driver  {

    //a method to read items from file and save it to the arrayList
    public static void read(Stock obj) {
        File file = new File("C:\\Users\\harit\\OneDrive\\Desktop\\Java2\\Java\\src\\hareth.txt");
        try {
            Scanner scan = new Scanner(file);
            StockItem stk;
            while (scan.hasNext()) {
                String st = scan.nextLine();
                String arr[] = st.split(", "); //split the line in the file
                if (arr[0].equalsIgnoreCase("Grocery")) {
                    String calendar[] = arr[5].split("/");//split the date if the item type is grocery
                    int day = Integer.parseInt(calendar[0]);
                    int month = Integer.parseInt(calendar[1]) - 1;//because month in calendar is 0-based
                    int year = Integer.parseInt(calendar[2]);
                    stk = new Grocery(arr[1], arr[2], Double.parseDouble(arr[3]), Double.parseDouble(arr[4]), new GregorianCalendar(year, month, day), Float.parseFloat(arr[6]));//create new grocery object
                } else {
                    stk = new Electronic(arr[1], arr[2], Double.parseDouble(arr[3]), Double.parseDouble(arr[4]), arr[5], Double.parseDouble(arr[6]));//create new grocery electrinic
                }
                obj.addStockItem(stk);
            }
            System.out.println();
            System.out.println();
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }
        System.out.println("done");
    }


    // a method to read from the user and save it in the arrayList and update the file
    public static void enterAnItem(Stock obj) {
        Scanner scan = new Scanner(System.in);
        String order = "Grocery";
        StockItem stk;
        System.out.println("enter 1 for Grocery and 2 for Electronic");
        String item = scan.nextLine();
        if (item.equals("1")) {
            System.out.println("enter the name of item");
            String name = scan.nextLine();

            System.out.println("enter the brand name");
            String brand = scan.nextLine();

            String discount;
            while (true) {
                System.out.println("enter the discount of the item");
                discount = scan.nextLine();
                try {
                    if (Double.parseDouble(discount) >= 0 && Double.parseDouble(discount) <= 100)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }

            String price;
            while (true) {
                System.out.println("enter the price of the item");
                price = scan.nextLine();
                try {
                    if (Double.parseDouble(price) >= 0)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }

            Calendar calendar = new GregorianCalendar();
            while (true) {
                System.out.println("enter the expired date of the item");

                System.out.println("enter the year: ");
                String year = scan.nextLine();

                System.out.println("enter the month: ");
                String month = scan.nextLine();

                System.out.println("enter the day of month: ");
                String day = scan.nextLine();
                try {
                    if (Grocery.isDateAvailable(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day))) {//check if the dat is available or not
                        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));
                        break;
                    }
                    System.out.println("invalid date , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }

            String weight;
            while (true) {
                System.out.println("enter the weight of the item");
                weight = scan.nextLine();
                try {
                    if (Float.parseFloat(weight) > 0)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a float number");
                }
            }


            obj.getItems().clear(); //clear the arrayList to avoid redundancy
            read(obj); //store the file content in the arrayList and add to the arrayList new object that entered from user
            stk = new Grocery(name, brand, Double.parseDouble(discount), Double.parseDouble(price), calendar, Float.parseFloat(weight));
            obj.addStockItem(stk);//add the object to the arrayList


        } else if (item.equals("2")) {//if the item is electronic

            order = "Electronics";
            System.out.println("enter the version of the item");
            String version = scan.nextLine();

            System.out.println("enter the brand name");
            String brand = scan.nextLine();

            String discount;
            while (true) {
                System.out.println("enter the discount of the item");
                discount = scan.nextLine();
                try {
                    if (Double.parseDouble(discount) >= 0 && Double.parseDouble(discount) <= 100)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }

            String price;
            while (true) {
                System.out.println("enter the price of the item");
                price = scan.nextLine();
                try {
                    if (Double.parseDouble(price) >= 0)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }

            System.out.println("enter the type of the item");
            String type = scan.nextLine();


            String power;
            while (true) {
                System.out.println("enter the power of the item");
                power = scan.nextLine();
                try {
                    if (Double.parseDouble(power) > 0)
                        break;
                    System.out.println("invalid input , try again");
                } catch (NumberFormatException e) {
                    System.out.println("invalid input , enter a double number");
                }
            }


            obj.getItems().clear(); //clear the arrayList to avoid redundancy
            read(obj);//store the file content in the arrayList and add to the arrayList new object that entered from user
            stk = new Electronic(version, brand, Double.parseDouble(discount), Double.parseDouble(price), type, Double.parseDouble(power));
            obj.addStockItem(stk);//add the object to the arrayList

        } else {
            System.out.println("invalid option , try again");
            enterAnItem(obj);
        }

        // save items to the file from the arrayList
        File file = new File("C:\\Users\\harit\\OneDrive\\Desktop\\Java2\\Java\\src\\hareth.txt");
        try (PrintWriter pr = new PrintWriter(file)) {

            int size = obj.getItems().size();
            for (int i = 0; i < size; i++) {
                if (obj.getItems().get(i) instanceof Electronic) {//check if the type of item is electronic or not
                    pr.print("Electronics, ");

                    //print the electronic fields
                    pr.print(((Electronic) obj.getItems().get(i)).getVersion() + ", ");
                    pr.print(((Electronic) obj.getItems().get(i)).getBrand() + ", ");
                    pr.print(((Electronic) obj.getItems().get(i)).getDiscount() + ", ");
                    pr.print(((Electronic) obj.getItems().get(i)).getPrice() + ", ");
                    pr.print(((Electronic) obj.getItems().get(i)).getType() + ", ");
                    pr.print(((Electronic) obj.getItems().get(i)).getPower());
                    pr.println();

                } else {
                    pr.print("Grocery, ");

                    //print the grocery fields
                    pr.print(((Grocery) obj.getItems().get(i)).getName() + ", ");
                    pr.print(((Grocery) obj.getItems().get(i)).getBrand() + ", ");
                    pr.print(((Grocery) obj.getItems().get(i)).getDiscount() + ", ");
                    pr.print(((Grocery) obj.getItems().get(i)).getPrice() + ", ");
                    pr.print(((Grocery) obj.getItems().get(i)).getExpirydate().get(Calendar.DAY_OF_MONTH)
                            + "/" + (((Grocery) obj.getItems().get(i)).getExpirydate().get(Calendar.MONTH) + 1)
                            + "/" + ((Grocery) obj.getItems().get(i)).getExpirydate().get(Calendar.YEAR) + ", ");
                    pr.print(((Grocery) obj.getItems().get(i)).getWeight());
                    pr.println();
                }
            }
            System.out.println("done");
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + e.getMessage());
        }

    }


    //a method to print all expired date grocery items
    public static void printExpired(Stock obj) {
        int flag = 0;
        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            if (obj.getItems().get(i) instanceof Grocery) {//check if the item is grocery
                if (Grocery.dateComparing(((Grocery) obj.getItems().get(i)).getExpirydate()) > 0) {//check if the date is expired or not
                    System.out.println(((Grocery) obj.getItems().get(i)).toString());
                    flag++;
                }
            }
        }
        if (flag == 0)
            System.out.println("there is no expired items");
    }


    // a method to print the brand name, type , and the price of items have a discount
    public static void printAfterDiscount(Stock obj) {
        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            if (obj.getItems().get(i) instanceof Electronic) {
                if (obj.getItems().get(i).getDiscount() > 0)
                    System.out.println(((Electronic) obj.getItems().get(i)).getBrand() + ", " + (((Electronic) obj.getItems().get(i)).getType() + " and the price is " +
                            ((Electronic) obj.getItems().get(i)).getPrice()));
            }
        }
    }


    //a method to sort the arrayList Based on characters using ASCII code and print it
    public static void sortByBrand(Stock obj) {
        Collections.sort(obj.getItems());
        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            System.out.println(obj.getItems().get(i).toString());
        }
    }


    //a method to sort the arrayList Based on price and print it
    public static void sortByPrice(Stock obj) {
        Collections.sort(obj.getItems(), new priceComparing());
        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            System.out.println(obj.getItems().get(i).toString());
        }
    }


    //a method takes a discount value and prints all items that have the same discount or less
    public static void printDiscount(Stock obj, double discount) {
        int flag = 0;
        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            if (obj.getItems().get(i).getDiscount() <= discount && obj.getItems().get(i).getDiscount() > 0) {
                if (obj.getItems().get(i) instanceof Grocery) {
                    System.out.println(((Grocery) obj.getItems().get(i)).toString());
                    flag++;
                } else {
                    System.out.println(((Electronic) obj.getItems().get(i)).toString());
                    flag++;
                }
            }
        }
        if (flag == 0)
            System.out.println("there is no items with this discount");
    }


    //a method to make a deep copy of the arrayList
    public static ArrayList<StockItem> cloning(Stock obj) {
        ArrayList<StockItem> clonedItems = new ArrayList<>();

        int size = obj.getItems().size();
        for (int i = 0; i < size; i++) {
            try {
                if (obj.getItems().get(i) instanceof Grocery) {
                    Grocery item = (Grocery) obj.getItems().get(i);
                    clonedItems.add(item.clone());
                } else {
                    Electronic item = (Electronic) obj.getItems().get(i);
                    clonedItems.add(item.clone());
                }
            } catch (CloneNotSupportedException e) {
                System.out.println(e.getMessage());
            }
        }
        return clonedItems;
    }


    public static void main(String[] args) {

        Stock obj = new Stock("Al-Mashhadawi");

        Scanner scan = new Scanner(System.in);
        String option = "0";

        do {
            System.out.println("Main Menu:");
            System.out.println("1. read items from a file");
            System.out.println("2. add items to the stock save it into a file");
            System.out.println("3. print all expired date grocery items");
            System.out.println("4. display electronics items with brand, type, and price after discount");
            System.out.println("5. print all stock items sorted by brand in descending order");
            System.out.println("6. print all stock items sorted by price in descending order");
            System.out.println("7. print all stock items with discounts");
            System.out.println("8. clone the ArrayList of stock");
            System.out.println("9. exit");
            System.out.print("enter your choice: ");


            option = scan.nextLine();

            switch (option) {
                case "1":
                    read(obj);
                    break;

                case "2":
                    enterAnItem(obj);
                    break;

                case "3":
                    printExpired(obj);
                    break;

                case "4":
                    printAfterDiscount(obj);
                    break;

                case "5":
                    sortByBrand(obj);
                    break;

                case "6":
                    sortByPrice(obj);
                    break;

                case "7":
                    System.out.println("enter the discount from 0 to 100");
                    String discount;
                    double discountValue = 0.0;

                    while (true) {
                        discount = scan.nextLine();
                        try {
                            discountValue = Double.parseDouble(discount);
                            if (discountValue >= 0 && discountValue <= 100.0) {//check if the discount value are available or not
                                break;
                            } else {
                                System.out.println("discount can not be negative , try again");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input , enter a numeric value");
                        }
                    }

//                    printDiscount(obj, discountValue);

                case "8":
                    cloning(obj);
                    break;

                case "9":
                    System.out.println("thanks for using the system");
                    break;

                default:
                    System.out.println("Invalid input, try again ");
            }

        } while (!option.equals("9"));


    }
}

