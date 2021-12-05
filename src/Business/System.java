
package Business;

import DataAccess.Concrete.XMLCustomerDataAccess;
import DataAccess.Concrete.XMLGalleryOwnerDataAccess;
import Entities.Concrete.Car;
import Entities.Concrete.Customer;
import Entities.Concrete.GalleryOwner;
import Entities.Concrete.Mail;
import java.util.ArrayList;

public class System {
    
    //Gallery Owner Management Methods
    
    public static void addGalleryOwner(String galleryName, String fullName, 
            String gender, int age, String imgPath, String phoneNumber, String mailAdress, String mailName,
            String Username, String Password, String resetPasswordQuestion, String resetPasswordAnswer)
    {
        XMLGalleryOwnerDataAccess.add(new GalleryOwner(galleryName, fullName, gender, age, imgPath, phoneNumber, 
                new Mail(mailAdress,mailName), 
                Username, Password, resetPasswordQuestion, resetPasswordAnswer));
    }
    
    public static void deleteGalleryOwner(GalleryOwner galleryOwner){
        XMLGalleryOwnerDataAccess.delete(galleryOwner);
    }
    
    public static void updateGalleryOwner(GalleryOwner galleryOwner){
        XMLGalleryOwnerDataAccess.update(galleryOwner);
    }
    
    public static ArrayList<GalleryOwner> getAllGalleryOwners(){
        return XMLGalleryOwnerDataAccess.getAll();
    }
    
    public static GalleryOwner getGalleryOwner(GalleryOwner galleryOwner){
        return XMLGalleryOwnerDataAccess.getById(galleryOwner);
    }

    public static void addCarToGalleryOwner(GalleryOwner galleryOwner, String brand, String model, 
            String modelYear, double price, boolean isNew, int km){
        
        XMLGalleryOwnerDataAccess.addCar(galleryOwner, new Car(brand, model, modelYear, price, isNew, km));
    }
    
    public static void deleteCarFromGalleryOwner(GalleryOwner galleryOwner, Car car){
        XMLGalleryOwnerDataAccess.deleteCar(galleryOwner, car);
    }
    
    public static void updateCarInGalleryOwner(GalleryOwner galleryOwner, Car car){
        XMLGalleryOwnerDataAccess.updateCar(galleryOwner, car);
    }
    
    public static ArrayList<Car> getAllCarsFromGalleryOwner(GalleryOwner galleryOwner){
        return XMLGalleryOwnerDataAccess.getAllCars(galleryOwner);
    }

    public static Car getCarByIdFromGalleryOwner(GalleryOwner galleryOwner, Car car){
        return XMLGalleryOwnerDataAccess.getByIdCar(galleryOwner, car);
    }
    
    //Gallery Owner Management Methods
    
    ///////////////////////////////////////////////////////////////////////////////////
    
    // Customer Management Methods
    
    public static void addCustomer(double totalCash, 
            String fullName, String gender, int age, String imgPath, 
            String phoneNumber, String mailAdress, String mailName, 
            String Username, String Password, 
            String resetPasswordQuestion, String resetPasswordAnswer){
        
        XMLCustomerDataAccess.add(new Customer(totalCash, fullName, gender, age, imgPath, phoneNumber, new Mail(mailAdress,mailName), Username, Password, resetPasswordQuestion, resetPasswordAnswer));
        
    }
    
    public static void deleteCustomer(Customer customer){
        XMLCustomerDataAccess.delete(customer);
    }
    
    public static void updateCustomer(Customer customer){
        XMLCustomerDataAccess.update(customer);
    }
    
    public static ArrayList<Customer> getAllCustomers(){
        return XMLCustomerDataAccess.getAll();
    }
    
    public static Customer getCustomer(Customer customer){
        return XMLCustomerDataAccess.getById(customer);
    }
    
    public static void depositMoneyForCustomer(Customer customer, double amount){
        XMLCustomerDataAccess.depositMoney(customer, amount);
    }
    
    // Customer Management Methods
}
