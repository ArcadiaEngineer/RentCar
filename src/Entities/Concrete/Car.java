
package Entities.Concrete;

public class Car {
    
    private int id;
    private String brand;
    private String model;
    private String modelYear;
    private double price;
    private boolean isNew;
    private int km;
    
    private static int total_id = 0;

    public Car(String brand, String model, String modelYear, double price, boolean isNew, int km) {
        this.id = total_id++;
        this.brand = brand;
        this.model = model;
        this.modelYear = modelYear;
        this.price = price;
        this.isNew = isNew;
        this.km = km;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + "\n" +
               "Model: " + model + "\n" +
               "Model Year: " + modelYear + "\n" + 
               "Price: " + price + "\n" + 
               (isNew == true ? "New Car" : "Used Car") + "\n" ;
                
    }
    
        
    
}
