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
    
    private static int total_id = 0;

    public Order(int orderId, String promotionCodeId, String fullName, String phoneNumber, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, String imgCarPath) {
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
        total_id++;
    }

    public static int getTotal_id() {
        return total_id;
    }

    public Order(int orderId, String fullName, String phoneNumber, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, String imgCarPath) {
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
    }

    public String getImgCarPath() {
        return imgCarPath;
    }

    public void setImgCarPath(String imgCarPath) {
        this.imgCarPath = imgCarPath;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPromotionCodeId() {
        return promotionCodeId;
    }

    public void setPromotionCodeId(String promotionCodeId) {
        this.promotionCodeId = promotionCodeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return this.brand + "  " +this.model;
    }
    
}
