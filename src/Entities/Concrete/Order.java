package Entities.Concrete;

import java.util.Date;

public class Order {
    
    private int orderId;
    private String promotionCodeId;
    private String fullName;
    private String phoneNumber;
    private String brand;
    private String model;
    private String rentDate;
    private String returnDate;
    private double amountPaid;
    private int galleryId;
    private int customerId;
    private String imgCarPath;
    private int totalDay;
    private double dailyPrice;
    
    private static int total_id = 0;

    public Order(int orderId, String promotionCodeId, String fullName, String phoneNumber, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, String imgCarPath, int totalDay, double dailyPrice) {
        this.orderId = orderId;
        this.promotionCodeId = promotionCodeId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.brand = brand;
        this.model = model;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.amountPaid = amountPaid;
        this.galleryId = galleryId;
        this.customerId = customerId;
        this.imgCarPath = imgCarPath;
        this.totalDay = totalDay;
        this.dailyPrice = dailyPrice;
        total_id++;
    }

    public Order(int orderId, String fullName, String phoneNumber, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, String imgCarPath, int totalDay, double dailyPrice) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.brand = brand;
        this.model = model;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.amountPaid = amountPaid;
        this.galleryId = galleryId;
        this.customerId = customerId;
        this.imgCarPath = imgCarPath;
        this.totalDay = totalDay;
        this.dailyPrice = dailyPrice;
        total_id++;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    
    

    public static int getTotal_id() {
        return total_id;
    }

    
    public String getImgCarPath() {
        return imgCarPath;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public String getPromotionCodeId() {
        return promotionCodeId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return this.brand + "  " +this.model;
    }
    
}
