package Entities.Concrete;

/**
 *
 * @author Lian La-Fey
 */
public class Car implements Comparable<Object> {
    
    private int id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private int km;
    private String imgPath;
    
    private static int total_id = 0;

    public Car(String brand, String model, int year, double price, int km, int id) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.km = km;
        total_id++;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getTotal_id() {
        return total_id;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + "\n" +
               "Model: " + model + "\n" +
               "Model Year: " + year + "\n" + 
               "Price: " + price + "\n";
                
    }

    @Override
    public int compareTo(Object obj) {
        return brand.compareToIgnoreCase( ((Car)obj).getBrand() );
    }
    
}
