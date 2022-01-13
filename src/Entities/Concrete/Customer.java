<<<<<<< HEAD
package Entities.Concrete;

import Entities.Abstract.User;
import GUI.Bill;

public class Customer extends User {
    
    private int customer_id;
    private double totalCash;
    
    private static int total_customer = 0;
    public static int current_database_customer_id;

    public Customer(String fullName, String gender, int age, String imgPath, String phoneNumber, Mail mailAdress, String Username, String homeAddress, String Password, String resetPasswordQuestion, String resetPasswordAnswer, double totalCash) {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, homeAddress, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.customer_id = current_database_customer_id++;
        this.totalCash = totalCash;
        total_customer++;
    }
    
    public Customer() {
        super("", "", 0, "", "", new Mail(), "", "", "", "", "");
        this.customer_id = current_database_customer_id++;
        this.totalCash = totalCash;
        total_customer++;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    public static int getTotal_customer() {
        return total_customer;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Total Cash: " + totalCash + "\n" ;
    }

    @Override
    public void printOrder(Order order) {
        Bill frameBill = new Bill( order );
        frameBill.setVisible( true );
        frameBill.setLocationRelativeTo( null );
    }
    
}
=======
package Entities.Concrete;

import Entities.Abstract.Person;

public class Customer extends Person{
    
    private int CustomerId;
    private double totalCash;
    
    private static int total_id = 0;

    public Customer(double totalCash, 
            String fullName, String gender, int age, String imgPath, 
            String phoneNumber, Mail mailAdress, String Username, String Password, 
            String resetPasswordQuestion, String resetPasswordAnswer) 
    {
        super(fullName, gender, age, imgPath, phoneNumber, mailAdress, Username, Password, resetPasswordQuestion, resetPasswordAnswer);
        this.CustomerId = total_id++;
        this.totalCash = totalCash;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Total Cash: " + totalCash + "\n" ;
    }
    
    
}
>>>>>>> master
