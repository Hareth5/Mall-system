package proj2;

public class Location {

    private String street , district , city;

    public Location() {//a no arg-constructor
    }

    public Location(String street, String district, String city) {//a parameterized constructor
        this.street = street;
        this.district = district;
        this.city = city;
    }

    @Override
    public String toString() {//a toString method for the location
        return "Location{" +
                "street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
