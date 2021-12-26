package Business;

import Entities.Abstract.User;
import Entities.CarComparators.*;
import Entities.Concrete.Car;
import Entities.Concrete.Customer;
import Entities.Concrete.Mail;
import DataAccessLayer.DBConnection;
import Entities.Concrete.Gallery;
import Entities.Concrete.GalleryOwner;
import Entities.Concrete.Order;
import Entities.Concrete.PromotionCode;
import Helper.HelperMethods;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 *
 * @author Lian La-Fey
 */
public class RentCarSystem {
    
    private static ArrayList<Mail> mailList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<PromotionCode> promotionCodes = new ArrayList<>();
    private static ArrayList<Gallery> galleries = new ArrayList<>();
    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    private static DBConnection dbConnection = new DBConnection();
    private static Connection connection = dbConnection.connDb();
    private static Statement st = null;
    private static ResultSet rs = null;
    
    ////////////////////////////////////////////////////////////////////////////
    
    public static ArrayList<User> getUserList() {
        return userList;
    }
    
    public static boolean addUserToList(User person) {
        return userList.add( person );
    }
    
    public static boolean addUserToDatabase(String ageStr, String emailName, String fullName, 
                                              String gender, String imgPath, String phoneNum, String userName, 
                                              String password, String rstPasswrdQue, String rstPasswrdAns) {
        
        double totalCash = (int)(Math.random() * 500) + 100;
        int age = Integer.parseInt(ageStr);
        
        if ( age < 18 )
            age = 18;
        
        Customer customer = null;
        Mail mail = null;
        
        try {
            mail = RentCarSystem.getMailByName( emailName );
            
            if ( mail == null )
                throw new NullPointerException();
            
            Customer.current_database_customer_id++;
            customer = new Customer(fullName, gender, age, imgPath, phoneNum, mail, 
                                    userName, password, rstPasswrdQue, rstPasswrdAns, totalCash);
            
        } catch (NullPointerException e) {
            HelperMethods.showErrorMessage("Please Check Your Email Adress", "Not Found Email");
        }
        
        
        
        String query = "INSERT INTO customer" + "(fullName, gender, age, total_cash, imgPath, phoneNum, mail_id, username, password, resetPasswordQue, resetPasswordAns, customer_id) VALUES" + 
                       "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            System.out.println(Customer.current_database_customer_id);
            st = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setString(2, customer.getGender());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.setDouble(4, customer.getTotalCash());
            preparedStatement.setString(5, customer.getImgPath());
            preparedStatement.setString(6, customer.getPhoneNumber());
            preparedStatement.setInt(7, customer.getMailAdress().getMail_id());
            preparedStatement.setString(8, customer.getUsername());
            preparedStatement.setString(9, customer.getPassword());
            preparedStatement.setString(10, customer.getResetPasswordQuestion());
            preparedStatement.setString(11, customer.getResetPasswordAnswer());
            preparedStatement.setInt(12, customer.getCustomerId());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        RentCarSystem.addUserToList(customer);
        
        return true;
    }
    
    public static User getUserByMailName( String mailName ) {
        for ( User user : userList ) {
            if ( user.getMailAdress().getName().equals( mailName ) ) {
                return user;
            }
        }
        
        return null;
    }
    
    public static User getUserByMailName_and_Password(String mailName, String password) {
        for ( User user : userList ) {
            
            if ( user.getMailAdress().getName().equals(mailName) && user.getPassword().equals(password) ) {
                return user;
            }
        }
        
        return null;
    }
    
    public static boolean updateUserPassword(String mailName, String newPassword) throws IllegalArgumentException {
        
        if ( !HelperMethods.checkPasswordWriting(newPassword) ) {
            throw new IllegalArgumentException("Please try another password!" + "\n"+
                                               "Do not use blanks!" + "\n" + 
                                               "Your passwor must include at least three characters!");
        } else {
            Customer customer = (Customer) RentCarSystem.getUserByMailName( mailName );
        
            customer.setPassword( newPassword );
            
            try {
                
                String query = "UPDATE customer SET password = " + customer.getPassword() + " WHERE customer_id = " + customer.getCustomerId();
            
                st = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate(); 
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return true;
    }
    
    public static GalleryOwner getGalleryOwnerById(int id) {
        for ( User user : userList ) {
            if ( user instanceof GalleryOwner && ((GalleryOwner)user).getGalleryOwner_id() == id ) 
                return (GalleryOwner) user;
        }
        
        return null;
    }
    
    public static Gallery getGalleryById(int id) {
        for ( Gallery gallery : galleries )
            if ( gallery.getId() == id )
                return gallery;
        
        return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Car> getCars(){
        return cars;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<PromotionCode> getPromotions(){
        return promotionCodes;
    }
    public static PromotionCode getPromotionCodeByCode(String code){
        for (PromotionCode promotionCode : promotionCodes) {
            if(promotionCode.getPromotionCode_str().equals(code)){
                return promotionCode;
            }
        }
        return null;
    }
    public static boolean  isUsed(PromotionCode promotionCode){
        return promotionCode.isIsUsed();
    }
    public static boolean createOrder(String promotionCode, String fullName, String phoneNumber,
            String brand ,String model,
            String rentDate, String returnDate, 
            double amountPaid, int galleryId, int customerId, String imgCarPath){
        PromotionCode promotionCodeforCheckCode = getPromotionCodeByCode(promotionCode);
        if(promotionCodeforCheckCode!=null && isUsed(promotionCodeforCheckCode)==false){
            amountPaid -= promotionCodeforCheckCode.getDiscount()*amountPaid;
            if(!fullName.isBlank() && !fullName.isEmpty() && !phoneNumber.isEmpty() && !phoneNumber.isBlank() && !brand.isBlank() && !brand.isEmpty() && !model.isBlank() && !model.isEmpty() && !rentDate.isBlank() && !rentDate.isEmpty() && !returnDate.isBlank() && !returnDate.isEmpty()){
                Order order = new Order(Order.getTotal_id(), promotionCodeforCheckCode.getPromotionCode_str(), fullName, phoneNumber, brand, model, rentDate, returnDate, amountPaid, galleryId, customerId, imgCarPath);
                var result = addOrderToDabase(order);
                if(result){
                    orders.add(order);
                    promotionCodeforCheckCode.setIsUsed(true);
                    updatePromotion(promotionCodeforCheckCode);
                    return true;
                }
            }
        }
        return false;//Promotion code exception
    }
    public static void createOrder(String fullName, String phoneNumber,
            String brand ,String model,
            String rentDate, String returnDate, 
            double amountPaid, int galleryId, int customerId , String imgCarPath){
        Order order = new Order(Order.getTotal_id(), fullName, phoneNumber, brand, model, rentDate, returnDate, amountPaid, galleryId, customerId , imgCarPath);
        var result = addOrderToDabase(order);
        if(result){
            orders.add(order);
        }
    }
    public static boolean addOrderToDabase(Order order){
        
        boolean result = false;
        
        String query = "INSERT INTO orders" + "( promotionCode, fullName, phoneNumber, brand, model, rentDate, returnDate, amountPaid, customerId, galleryId, carImgPath) VALUES" + 
                       "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            st = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement( query );
            preparedStatement.setString(1, order.getPromotionCodeId());
            preparedStatement.setString(2, order.getFullName());
            preparedStatement.setString(3, order.getPhoneNumber());
            preparedStatement.setString(4, order.getBrand());
            preparedStatement.setString(5, order.getModel());
            preparedStatement.setString(6, order.getRentDate());
            preparedStatement.setString(7, order.getReturnDate());
            preparedStatement.setDouble(8, order.getAmountPaid());
            preparedStatement.setInt(9, order.getCustomerId());
            preparedStatement.setInt(10, order.getGalleryId());
            preparedStatement.setString(11, order.getImgCarPath());
            preparedStatement.executeUpdate();
            
            result = true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = false;
        }
        
        return result;
    }
    public static void updatePromotion(PromotionCode promotionCode){
        try {
                
                String query = "UPDATE promotionCodes SET isUsed = " + promotionCode.isIsUsed() + " WHERE promotionCode_id = " + promotionCode.getPromotionCode_id();
            
                st = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate(); 
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
    public static Order getOrderById(int id){
        for (Order order : orders) {
            if(order.getOrderId() == id){
                return order;
            }
        }
        return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Gallery> getGalleries(){
        return galleries;
    }
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Order> getOrders(){
        return orders;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    public static void getDataFromDatabase() {
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM mail");
            
            while( rs.next() ) {
                Mail mail = new Mail(rs.getInt("mail_id"), rs.getString("name"));
                RentCarSystem.addMail( mail );
            }
            
            rs = st.executeQuery("SELECT * FROM customer");
            
            while ( rs.next() ) {
                Customer.current_database_customer_id = rs.getInt("customer_id");
                Mail mail = RentCarSystem.getMailById( rs.getInt("mail_id") );
                Customer customer = new Customer(rs.getString("fullName"),
                        rs.getString("gender"), rs.getInt("age"), rs.getString("imgPath"), rs.getString("phoneNum"), mail,
                        rs.getString("username"), rs.getString("password"), rs.getString("resetPasswordQue"), rs.getString("resetPasswordAns"), rs.getDouble("total_cash"));
                RentCarSystem.addUserToList(customer );
            }
            
            rs = st.executeQuery("SELECT * FROM galleryowner");
            
            while ( rs.next() ) {
                Mail mail = RentCarSystem.getMailById( rs.getInt("mail_id") );
                GalleryOwner galleryOwner = new GalleryOwner(rs.getInt("galleryOwner_id"), rs.getString("fullName"), 
                        rs.getString("gender"), rs.getInt("age"), rs.getString("imgPath"), rs.getString("phoneNum"), mail,
                        rs.getString("username"), rs.getString("password"), rs.getString("resetPasswordQue"), 
                        rs.getString("resetPasswordAns"));
                RentCarSystem.addUserToList( galleryOwner );
            }
            
            rs = st.executeQuery("SELECT * FROM promotioncodes");
            
            while ( rs.next() ) {
                boolean isUsed = rs.getBoolean("isUsed");
                PromotionCode promotionCode = new PromotionCode( rs.getInt("promotionCode_id"), rs.getString("promotionCode_str"), rs.getDouble("discount"), isUsed);
                promotionCodes.add( promotionCode );
                
            }
            
            rs = st.executeQuery("SELECT * FROM orders");
            
            while ( rs.next() ) {
                
                Order order = new Order(rs.getInt("order_id"),rs.getString("promotionCode"),rs.getString("fullName"),rs.getString("phoneNumber"),
                rs.getString("brand"),rs.getString("model"),rs.getString("rentDate"),
                rs.getString("returnDate"),rs.getDouble("amountPaid"),rs.getInt("customerId"),
                rs.getInt("galleryId"),rs.getString("carImgPath"));
                orders.add(order);
                
            }
            
            rs = st.executeQuery("SELECT * FROM galleries");
            
            while ( rs.next() ) {
                
                Gallery gallery = new Gallery(rs.getInt("gallery_id"), rs.getString("name"));
                galleries.add( gallery );
                getGalleryOwnerById( rs.getInt("galleryOwner_id") ).getGalleries().add( gallery );
                
            }
            
            rs = st.executeQuery("SELECT * FROM cars");
            
            while ( rs.next() ) {
                
                Car car = new Car(rs.getInt("car_id"),rs.getInt("gallery_id"),rs.getString("brand"), rs.getString("model"), rs.getString("type"), rs.getString("fuelType"), 
                        rs.getString("transmissionType"), rs.getInt("year"), rs.getDouble("daily_price"), rs.getDouble("fuelCapacity"), 
                        rs.getDouble("trunkVolume"), rs.getInt("km"),rs.getString("small_imgPath"), rs.getString("large_imgPath"));
                cars.add( car );
                getGalleryById( rs.getInt("gallery_id") ).getCars().add( car );
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public static ArrayList<Mail> getMailList() {
        return mailList;
    }
    
    public static boolean addMail(Mail mail) {
        return mailList.add( mail );
    }
    
    public static Mail getMailById(int id) {
        for ( Mail mail : mailList ) 
            if ( mail.getMail_id() == id )
                return mail;
        
        return null;
    }
    
    public static Mail getMailByName(String name) throws NullPointerException {
        for ( Mail mail : mailList ) 
            if ( mail.getName().equals( name ) )
                return mail;
        
        return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public static void sortCarList(ArrayList<Car> cars, String accordingTo) {
        
        TreeSet<Car> sortedCars = null;
        
        switch ( accordingTo ) {
            
            case "Year" : sortedCars = new TreeSet<>( new CarYearComparator() );
                          sortedCars.addAll(cars);
                          cars.clear();
                          cars.addAll(sortedCars);
                          break;
            
            case "Price" : sortedCars = new TreeSet<>( new CarPriceComparator() );
                          sortedCars.addAll(cars);
                          cars.clear();
                          cars.addAll(sortedCars);
                          break;
            
            case "Kilometer" : sortedCars = new TreeSet<>( new CarKilometerComparator() );
                               sortedCars.addAll(cars);
                               cars.clear();
                               cars.addAll(sortedCars);
                               break;
                               
            case "Default" : Collections.sort(cars);
                             break;
        }
        
    }
}
