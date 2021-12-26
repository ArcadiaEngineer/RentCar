package Entities.Concrete;

/**
 *
 * @author Lian La-Fey
 */
public class Car implements Comparable<Object> {
    
    private int id;
    private int galleryId;
    private String brand;
    private String model;
    private String type;
    private String fuelType;
    private String transmissionType;
    private int year;
    private double price;
    private double fuelCapacity;
    private double trunkVolume;
    private int km;
    private String small_imgPath;
    private String large_imgPath;
    
    private static int total_id = 0;

    public Car(int id, int galleryId, String brand, String model,
            String type, String fuelType, String transmissionType, int year,
            double price, double fuelCapacity, double trunkVolume, int km,
            String small_imgPath, String large_imgPath) {
        this.id = id;
        this.galleryId = galleryId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.year = year;
        this.price = price;
        this.fuelCapacity = fuelCapacity;
        this.trunkVolume = trunkVolume;
        this.km = km;
        this.small_imgPath = small_imgPath;
        this.large_imgPath = large_imgPath;
        total_id++;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
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

    public String getType() {
        return type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getTrunkVolume() {
        return trunkVolume;
    }

    public String getSmall_imgPath() {
        return small_imgPath;
    }

    public String getLarge_imgPath() {
        return large_imgPath;
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
