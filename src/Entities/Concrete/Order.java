package Entities.Concrete;

public class Order {
    
    private int orderId;
    private String promotionCodeId;
    private String fullName;
    private String gallOwn_PhoneNumber;
    private String cust_PhoneNumber;
    private String gallOwn_homeAddress;
    private String cust_homeAddress;
    private String brand;
    private String model;
    private String rentDate;
    private String returnDate;
    private double amountPaid;
    private int galleryId;
    private int customerId;
    private int carId;
    private String imgCarPath;
    private int totalDay;
    private double dailyPrice;
    
    private static int total_id = 0;

    public Order(int orderId, String promotionCodeId, String fullName, String gallOwn_PhoneNumber, String cust_PhoneNumber, String gallOwn_homeAddress, String cust_homeAddress, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, int carId, String imgCarPath, int totalDay, double dailyPrice) {
        this.orderId = orderId;
        this.promotionCodeId = promotionCodeId;
        this.fullName = fullName;
        this.gallOwn_PhoneNumber = gallOwn_PhoneNumber;
        this.cust_PhoneNumber = cust_PhoneNumber;
        this.gallOwn_homeAddress = gallOwn_homeAddress;
        this.cust_homeAddress = cust_homeAddress;
        this.brand = brand;
        this.model = model;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.amountPaid = amountPaid;
        this.galleryId = galleryId;
        this.customerId = customerId;
        this.carId = carId;
        this.imgCarPath = imgCarPath;
        this.totalDay = totalDay;
        this.dailyPrice = dailyPrice;
        total_id++;
    }

    

    public Order(int orderId, String fullName, String gallOwn_PhoneNumber, String cust_PhoneNumber, String gallOwn_homeAddress, String cust_homeAddress, String brand, String model, String rentDate, String returnDate, double amountPaid, int galleryId, int customerId, int carId, String imgCarPath, int totalDay, double dailyPrice) {
        this.orderId = orderId;
        this.fullName = fullName;
        this.gallOwn_PhoneNumber = gallOwn_PhoneNumber;
        this.cust_PhoneNumber = cust_PhoneNumber;
        this.gallOwn_homeAddress = gallOwn_homeAddress;
        this.cust_homeAddress = cust_homeAddress;
        this.brand = brand;
        this.model = model;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.amountPaid = amountPaid;
        this.galleryId = galleryId;
        this.customerId = customerId;
        this.carId = carId;
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

    public String getGallOwn_PhoneNumber() {
        return gallOwn_PhoneNumber;
    }

    public String getCust_PhoneNumber() {
        return cust_PhoneNumber;
    }

    public String getGallOwn_homeAddress() {
        return gallOwn_homeAddress;
    }

    public String getCust_homeAddress() {
        return cust_homeAddress;
    }

    public int getCarId() {
        return carId;
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
        return gallOwn_PhoneNumber;
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
        return "Order{" + "orderId=" + orderId + ", promotionCodeId=" + promotionCodeId + ", fullName=" + fullName + ", gallOwn_PhoneNumber=" + gallOwn_PhoneNumber + ", cust_PhoneNumber=" + cust_PhoneNumber + ", gallOwn_homeAddress=" + gallOwn_homeAddress + ", cust_homeAddress=" + cust_homeAddress + ", brand=" + brand + ", model=" + model + ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", amountPaid=" + amountPaid + ", galleryId=" + galleryId + ", customerId=" + customerId + ", carId=" + carId + ", imgCarPath=" + imgCarPath + ", totalDay=" + totalDay + ", dailyPrice=" + dailyPrice + '}';
    }

    
    
}
