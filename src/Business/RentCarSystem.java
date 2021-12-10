package Business;

import Entities.Abstract.User;
import Entities.CarComparators.*;
import Entities.Concrete.Car;
import Entities.Concrete.Customer;
import Entities.Concrete.Mail;
import database_access.DBConnection;
import helpers.HelperMethods;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 *
 * @author Lian La-Fey
 */
public class RentCarSystem {
    
    private static ArrayList<Mail> mailList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();

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
        for ( User person : userList ) {
            if ( person.getMailAdress().getName().equals( mailName ) ) {
                return person;
            }
        }
        
        return null;
    }
    
    public static boolean updatePersonPassword(String mailName, String newPassword) throws IllegalArgumentException {
        
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
